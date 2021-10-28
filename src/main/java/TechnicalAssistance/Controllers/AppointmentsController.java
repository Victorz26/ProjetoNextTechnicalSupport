package TechnicalAssistance.Controllers;

import TechnicalAssistance.Entities.Appointments;
import TechnicalAssistance.Entities.ScheduleAppointments;
import TechnicalAssistance.Entities.Slots;
import TechnicalAssistance.Services.AppointmentsService;
import TechnicalAssistance.Services.AssistancesService;
import TechnicalAssistance.Services.CustomersService;
import TechnicalAssistance.Services.ProductsService;
import TechnicalAssistance.Utils.Constants;
import TechnicalAssistance.Validations.WarrantyValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
    public ResponseEntity<Void> postAppointment(@RequestBody ScheduleAppointments scheduleAppointments) throws URISyntaxException {
        boolean underWarranty  = true;
        if(!WarrantyValidation.validateWarranty(scheduleAppointments)){
            underWarranty = false;
            return ResponseEntity.badRequest().build();
        }

        if(underWarranty) {
            Appointments appointments = new Appointments();

            appointments.setDates(LocalDate.parse(scheduleAppointments.getDate()));
            appointments.setHours(LocalTime.parse(scheduleAppointments.getHour()));
            appointments.setPurchaseDate(LocalDate.parse(scheduleAppointments.getPurchaseDate()));

            appointments.setIdCustomer(customersService.getCustomer(scheduleAppointments.getIdCustomer()));
            appointments.setIdAssistance(assistancesService.getAssistance(scheduleAppointments.getIdAssistance()));
            appointments.setIdProduct(productsService.getProduct(scheduleAppointments.getIdProduct()));

            appointmentsService.postAppointment(appointments);

            return ResponseEntity.created(new URI(Constants.URL + "appointments/" + appointments.getId())).build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointments> getAppointment(@PathVariable Long id){
        Appointments appointments = appointmentsService.getAppointment(id);
        return ResponseEntity.ok().body(appointments);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity.BodyBuilder deleteAppointment(@PathVariable Long id){
        appointmentsService.deleteAppointment(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED);
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
