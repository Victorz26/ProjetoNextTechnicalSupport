package Assistencias.Services;

import Assistencias.DAO.AssistenciaDAO;
import Assistencias.Entities.Assistencias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssistenciasService {

    @Autowired
    private AssistenciaDAO assistenciaDAO;

    public void salvar(Assistencias assistencias) {
        assistenciaDAO.save(assistencias);
    }
    public List<Assistencias> findAll() {
        return assistenciaDAO.findAll();
    }

    public Assistencias find (Long id){
        return assistenciaDAO.findById(id).get();
    }

    public void delete (Long id) {
        assistenciaDAO.deleteById(id);
    }
}
