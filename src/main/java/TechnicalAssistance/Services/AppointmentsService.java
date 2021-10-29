package TechnicalAssistance.Services;

import TechnicalAssistance.DAO.AppointmentsDAO;
import TechnicalAssistance.Entities.Appointments;
import TechnicalAssistance.Entities.Slots;
import TechnicalAssistance.Utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.ArrayList;
import java.time.*;

@Service
public class AppointmentsService {
    @Autowired
    private AppointmentsDAO appointmentsDAO;

    public void postAppointment(Appointments appointments) {
        appointmentsDAO.save(appointments);
    }

    public List<Appointments> getAllAppointments() {
        return appointmentsDAO.findAll();
    }

    public Appointments getAppointment(Long id){
        return appointmentsDAO.findById(id).get();
    }

    public void deleteAppointment(Long id) {
        appointmentsDAO.deleteById(id);
    }
  
    public List<Slots> getAssistanceWeekFreeSlots(Long id){

        List<Appointments> appointments = appointmentsDAO.findAll();
        List<Slots> filledSlots = new ArrayList<>();
        for (Appointments appointment: appointments) {
            if(appointment.getIdAssistance().getId().equals(id)) {
                filledSlots.add(new Slots(appointment.getIdAssistance().getName(), appointment.getDates(), appointment.getHours()));
            }
        }
        LocalDate today = LocalDate.now();
        List<Slots> freeSlots = new ArrayList<>();
        for (Slots slot: filledSlots) {
            for(int days = 1; days <= 7; days++) {
                for (LocalTime time = Constants.START_OF_DAY; time.isBefore(Constants.END_OF_DAY); time = time.plusMinutes(30)){
                    if(!filledSlots.contains(new Slots(slot.getAssistanceName(), today.plusDays(days), time))){
                        freeSlots.add(new Slots(slot.getAssistanceName(), today.plusDays(days), time));
                    }
                }
            }
        }

        return freeSlots;
    }

    public List<LocalTime> getAssistanceDayFreeSlots(Long id, String date){
        List<Appointments> appointments = appointmentsDAO.findAll();
        List<LocalTime> filledSlots = new ArrayList<>();

        for (Appointments appointment: appointments) {
            if(appointment.getIdAssistance().getId().equals(id) &&
                    appointment.getDates().equals(LocalDate.parse(date))) {
                filledSlots.add(appointment.getHours());
            }
        }

        List<LocalTime> freeSlots = new ArrayList<>();

        for (LocalTime time = Constants.START_OF_DAY; time.isBefore(Constants.END_OF_DAY); time = time.plusMinutes(30)){
            if(!filledSlots.contains(time)) {
                freeSlots.add(time);
            }
        }

        return freeSlots;
    }

    public List<Slots> getDayScheduling(String date){
        List<Appointments> appointments = appointmentsDAO.findAll();
        List<Slots> dayScheduling = new ArrayList<>();

        for (Appointments appointment: appointments) {
            if(appointment.getDates().equals(LocalDate.parse(date))){
                dayScheduling.add(new Slots(appointment.getIdAssistance().getName(),
                        appointment.getDates(), appointment.getHours()));
            }
        }

        return dayScheduling;
    }
}
