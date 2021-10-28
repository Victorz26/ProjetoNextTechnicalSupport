package TechnicalAssistance.Controllers;

import TechnicalAssistance.Entities.Clientes;
import TechnicalAssistance.Services.ClientesService;
import TechnicalAssistance.Utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClientesService clientesService;

    @GetMapping("/")
    public ResponseEntity<List<Clientes>> getTodosClientes() {
        List<Clientes> clientes = clientesService.getTodosClientes();
        return ResponseEntity.ok().body(clientes);
    }

    @PostMapping("/")
    public ResponseEntity<Void> postCliente(@RequestBody Clientes cliente) throws URISyntaxException {
        clientesService.postCliente(cliente);
        return ResponseEntity.created(new URI(Constants.URL+ "clientes/" + cliente.getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clientes> getCliente(@PathVariable Long id){
        Clientes cliente = clientesService.getCliente(id);
        return ResponseEntity.ok().body(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity.BodyBuilder deletaCliente(@PathVariable Long id){
        clientesService.deletaCliente(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED);
    }

}
