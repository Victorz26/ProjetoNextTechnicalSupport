package Assistencias.Services;

import Assistencias.DAO.AgendamentosDAO;
import Assistencias.DAO.AssistenciaDAO;
import Assistencias.Entities.Agendamentos;
import Assistencias.Entities.Slots;
import Assistencias.Utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Slots> getFreeSlots(Long id){
        //Criação de lista com horários ocupados
        List<Agendamentos> agendamentosList = agendamentosDAO.findAll();
        List<Slots> filledSlots = new ArrayList<>();

        for (Agendamentos agendamento: agendamentosList) {
            if(agendamento.getIdAssistencia().getId().equals(id)) {
                filledSlots.add(new Slots(agendamento.getData(), agendamento.getHorario()));
            }
        }

        //Criação de lista de horários livres
        LocalDate today = LocalDate.now();
        List<Slots> freeSlots = new ArrayList<>();

        for(int days = 1; days <= 7; days++) {
            for (LocalTime time = Constants.START_OF_DAY; time.isBefore(Constants.END_OF_DAY); time = time.plusMinutes(30)){
                if(!filledSlots.contains(new Slots(today.plusDays(days), time))) {
                    freeSlots.add(new Slots(today.plusDays(days), time));
                }
            }
        }

        return freeSlots;
    }
}
