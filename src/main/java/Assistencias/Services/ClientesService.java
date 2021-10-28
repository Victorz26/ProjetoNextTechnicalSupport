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

    public void postCliente(Clientes clientes) {
        clientesDAO.save(clientes);

    }

    public List<Clientes> getTodosClientes() {
        return clientesDAO.findAll();
    }

    public Clientes getCliente(Long id){
        return clientesDAO.findById(id).get();
    }

    public void deletaCliente(Long id) {
        clientesDAO.deleteById(id);
    }
}
