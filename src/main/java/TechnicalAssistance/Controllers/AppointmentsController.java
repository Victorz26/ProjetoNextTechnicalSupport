package TechnicalAssistance.Controllers;

import TechnicalAssistance.Entities.Appointments;
import TechnicalAssistance.Entities.ScheduleAppointments;
import TechnicalAssistance.Entities.Slots;
import TechnicalAssistance.Services.AppointmentsService;
import TechnicalAssistance.Services.AssistancesService;
import TechnicalAssistance.Services.CustomersService;
import TechnicalAssistance.Services.ProductsService;
import TechnicalAssistance.Utils.Constants;
import TechnicalAssistance.Validations.ValidateDateTime;
import TechnicalAssistance.Validations.WarrantyValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/appointments")
public class AppointmentsController {
    @Autowired
    private ProductsService productsService;
    @Autowired
    private AssistancesService assistancesService;
    @Autowired
    private CustomersService customersService;
    @Autowired
    private AppointmentsService appointmentsService;

    @GetMapping("/")
    public ResponseEntity<List<Appointments>> getAllAppointments() {
        List<Appointments> appointments = appointmentsService.getAllAppointments();
        return ResponseEntity.ok().body(appointments);
    }

    @PostMapping({"/", ""})
    public ResponseEntity<String> postAppointment(@RequestBody ScheduleAppointments scheduleAppointments) throws URISyntaxException {

        if(!WarrantyValidation.validateWarranty(scheduleAppointments)) {
            return ResponseEntity.badRequest().body("Não é possível realizar agendamento para produto fora da garantia.");
        }

        if ((ValidateDateTime.validateDate(scheduleAppointments.getDate())) || (ValidateDateTime.validateTime(scheduleAppointments.getHour()))) {
            return ResponseEntity.badRequest().body("Data ou hora inválida.");
        }

        Appointments appointments = new Appointments();

        appointments.setDates(LocalDate.parse(scheduleAppointments.getDate()));
        appointments.setHours(LocalTime.parse(scheduleAppointments.getHour()));
        appointments.setPurchaseDate(LocalDate.parse(scheduleAppointments.getPurchaseDate()));

        try{
            appointments.setIdCustomer(customersService.getCustomer(scheduleAppointments.getIdCustomer()));
        }catch (NoSuchElementException e){
            return ResponseEntity.badRequest().body("Cliente inexistente!");
        }

        try{
            appointments.setIdAssistance(assistancesService.getAssistance(scheduleAppointments.getIdAssistance()));
        }catch (NoSuchElementException e){
            return ResponseEntity.badRequest().body("Assistência inexistente!");
        }

        try{
            appointments.setIdProduct(productsService.getProduct(scheduleAppointments.getIdProduct()));
        }catch (NoSuchElementException e){
            return ResponseEntity.badRequest().body("Produto inexistente!");
        }

        try{
            appointmentsService.postAppointment(appointments);
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Conflito de agendamento!");
        }

        return ResponseEntity.created(new URI(Constants.URL + "appointments/" + appointments.getId())).body("Agendamento realizado com sucesso!\nCódigo do agendamento: " + appointments.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getAppointment(@PathVariable Long id){
        Appointments appointments = new Appointments();

        try{
            appointments = appointmentsService.getAppointment(id);
        }catch (NoSuchElementException e){
            return ResponseEntity.badRequest().body("Agendamento inexistente!");
        }

        return ResponseEntity.ok().body(appointments.toString());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id){
        appointmentsService.deleteAppointment(id);
        return ResponseEntity.ok().body("Agendamento excluído com sucesso!");
    }

    @GetMapping("/assistanceweekfreeslots/{id}")
    public ResponseEntity<List<Slots>> getWeekFreeSlots(@PathVariable Long id){
        List<Slots> freeSlots = appointmentsService.getAssistanceWeekFreeSlots(id);
        return ResponseEntity.ok().body(freeSlots);
    }

    @GetMapping("/assistancedayfreeslots/{id}/{date}")
    public ResponseEntity<List<LocalTime>> getWeekFreeSlots(@PathVariable Long id, @PathVariable String date){
        List<LocalTime> freeSlots = appointmentsService.getAssistanceDayFreeSlots(id, date);
        return ResponseEntity.ok().body(freeSlots);
    }

    @GetMapping("/dayscheduling/{date}")
    public ResponseEntity<List<Slots>> getWeekFreeSlots(@PathVariable String date){
        List<Slots> dayScheduling = appointmentsService.getDayScheduling(date);
        return ResponseEntity.ok().body(dayScheduling);
    }
}
