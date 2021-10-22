package Assistencias.Controllers;

import Assistencias.Entities.Clientes;
import Assistencias.Services.ClientesService;
import Assistencias.Utils.Constants;
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

    @GetMapping("/all")
    public ResponseEntity<List<Clientes>> findall() {
        List<Clientes> clientes = clientesService.findAll();
        return ResponseEntity.ok().body(clientes);
    }

    @PostMapping("/")
    public ResponseEntity<Void> save(@RequestBody Clientes cliente) throws URISyntaxException {
        clientesService.salvar(cliente);
        return ResponseEntity.created(new URI(Constants.URL+ "clientes/" + cliente.getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clientes> find(@PathVariable Long id){
        Clientes cliente = clientesService.find(id);
        return ResponseEntity.ok().body(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity.BodyBuilder delete(@PathVariable Long id){
        clientesService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED);
    }

}
