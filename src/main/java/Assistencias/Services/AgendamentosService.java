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

    public void salvar(Agendamentos agendamentos) {
        agendamentosDAO.save(agendamentos);
    }
    public List<Agendamentos> findAll() {
        return agendamentosDAO.findAll();
    }

    public Agendamentos find (Long id){
        return agendamentosDAO.findById(id).get();
    }

    public void delete (Long id) {
        agendamentosDAO.deleteById(id);
    }
}
