package Assistencias.Services;

import Assistencias.DAO.ClientesDAO;
import Assistencias.Entities.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientesService {

    @Autowired
    private ClientesDAO clientesDAO;

    public void salvar(Clientes clientes) {
        clientesDAO.save(clientes);

    }
    public List<Clientes> findAll() {
        return clientesDAO.findAll();
    }

    public Clientes find (Long id){
        return clientesDAO.findById(id).get();
    }

    public void delete (Long id) {
        clientesDAO.deleteById(id);
    }
}
