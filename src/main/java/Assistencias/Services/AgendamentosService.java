package Assistencias.Services;

import Assistencias.DAO.AgendamentosDAO;
import Assistencias.Entities.Agendamentos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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
}
