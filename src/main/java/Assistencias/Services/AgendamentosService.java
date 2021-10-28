package Assistencias.Services;

import Assistencias.DAO.AgendamentosDAO;
import Assistencias.Entities.Agendamentos;
import Assistencias.Entities.Slots;
import Assistencias.Utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.ArrayList;
import java.util.List;
import java.time.*;

@Service
public class AgendamentosService {
    @Autowired
    private AgendamentosDAO agendamentosDAO;

    public void postAgendamento(Agendamentos agendamentos) {
        agendamentosDAO.save(agendamentos);
    }

    public List<Agendamentos> getTodosAgendamentos() {
        return agendamentosDAO.findAll();
    }

    public Agendamentos getAgendamento(Long id){
        return agendamentosDAO.findById(id).get();
    }

    public void deletaAgendamento(Long id) {
        agendamentosDAO.deleteById(id);
    }
  
    public List<Slots> getAssistanceWeekFreeSlots(Long id){
        //Criação de lista com horários ocupados
        List<Agendamentos> schedules = agendamentosDAO.findAll();
        List<Slots> filledSlots = new ArrayList<>();

        for (Agendamentos scheduling: schedules) {
            if(scheduling.getIdAssistencia().getId().equals(id)) {
                filledSlots.add(new Slots(scheduling.getIdAssistencia().getNome(), scheduling.getData(), scheduling.getHorario()));
            }
        }

        //Criação de lista de horários livres
        LocalDate today = LocalDate.now();
        List<Slots> freeSlots = new ArrayList<>();
        for (Slots slot: filledSlots) {
            for(int days = 1; days <= 7; days++) {
                for (LocalTime time = Constants.START_OF_DAY; time.isBefore(Constants.END_OF_DAY); time = time.plusMinutes(30)){
                    if(!filledSlots.contains(new Slots(slot.getNomeAssistencia(), today.plusDays(days), time))){
                        freeSlots.add(new Slots(slot.getNomeAssistencia(), today.plusDays(days), time));
                    }
                }
            }
        }

        return freeSlots;
    }

    public List<LocalTime> getAssistanceDayFreeSlots(Long id, String date){
        List<Agendamentos> schedules = agendamentosDAO.findAll();
        List<LocalTime> filledSlots = new ArrayList<>();

        for (Agendamentos appointments: schedules) {
            if(appointments.getIdAssistencia().getId().equals(id) &&
                    appointments.getData().equals(LocalDate.parse(date))) {
                filledSlots.add(appointments.getHorario());
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
        List<Agendamentos> schedules = agendamentosDAO.findAll();
        List<Slots> dayScheduling = new ArrayList<>();

        for (Agendamentos scheduling: schedules) {
            if(scheduling.getData().equals(LocalDate.parse(date))){
                dayScheduling.add(new Slots(scheduling.getIdAssistencia().getNome(),
                        scheduling.getData(), scheduling.getHorario()));
            }
        }

        return dayScheduling;
    }
}
