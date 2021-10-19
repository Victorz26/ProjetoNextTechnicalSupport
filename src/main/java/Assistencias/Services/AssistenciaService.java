package Assistencias.Services;

import Assistencias.DAO.AssistenciaDAO;
import Assistencias.DAO.ClienteDAO;
import Assistencias.Entities.Assistencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class AssistenciaService {

    @Autowired
    private AssistenciaDAO assistenciaDAO;

    //Listar horarios/assistencias
    public List<Assistencia> findAll() {
        return assistenciaDAO.findAll();
    }

    public List<> findAll() {
        return assistenciaDAO.findAll();
}
