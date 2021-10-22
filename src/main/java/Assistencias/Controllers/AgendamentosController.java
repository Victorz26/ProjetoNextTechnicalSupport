package Assistencias.Controllers;

import Assistencias.Entities.Agendamentos;
import Assistencias.Entities.Clientes;
import Assistencias.Services.AgendamentosService;
import Assistencias.Services.AssistenciasService;
import Assistencias.Services.ClientesService;
import Assistencias.Services.ProdutosService;
import Assistencias.Utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentosController {
    @Autowired
    private ProdutosService produtosService;
    @Autowired
    private AssistenciasService assistenciasService;
    @Autowired
    private ClientesService clientesService;
    @Autowired
    private AgendamentosService agendamentosService;

    @GetMapping("/all")
    public ResponseEntity<List<Agendamentos>> findall() {
        List<Agendamentos> agendamentos = agendamentosService.findAll();
        return ResponseEntity.ok().body(agendamentos);
    }

    @PostMapping("/")
    public ResponseEntity<Void> save(Date data, Time horario, Long idCliente, Long idAssistencia, Long idProduto) throws URISyntaxException {
        Agendamentos agendamentos = new Agendamentos();
        agendamentos.setData(data);
        agendamentos.setHorario(horario);

        System.out.println("####Cliente");
        Clientes clientes = clientesService.find(idCliente);
        System.out.println(clientes);
        //agendamentos.setIdCliente(clientesService.find(idCliente));

        System.out.println("####Assistencia");
        System.out.println(assistenciasService.find(idAssistencia));
        //agendamentos.setIdAssistencia(assistenciasService.find(idAssistencia));

        System.out.println("####Produto");
        System.out.println(produtosService.find(idProduto));
        //agendamentos.setIdProduto(produtosService.find(idProduto));

        System.out.println(agendamentos);

        //agendamentosService.salvar(agendamentos);

        return ResponseEntity.created(new URI(Constants.URL+ "agendamentos/" + agendamentos.getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamentos> find(@PathVariable Long id){
        Agendamentos agendamentos = agendamentosService.find(id);
        return ResponseEntity.ok().body(agendamentos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity.BodyBuilder delete(@PathVariable Long id){
        agendamentosService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED);
    }

}
