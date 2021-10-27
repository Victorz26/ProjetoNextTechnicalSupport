package Assistencias.Controllers;

import Assistencias.Entities.Agendamentos;
import Assistencias.Entities.MarcacaoAgendamentos;
import Assistencias.Entities.Slots;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    @GetMapping("/")
    public ResponseEntity<List<Agendamentos>> getTodosAgendamentos() {
        List<Agendamentos> agendamentos = agendamentosService.getTodosAgendamentos();
        return ResponseEntity.ok().body(agendamentos);
    }

    @PostMapping("/")
    public ResponseEntity<Void> postAgendamentos(@RequestBody MarcacaoAgendamentos marcacaoAgendamentos) throws URISyntaxException {
        Agendamentos agendamentos = new Agendamentos();

        agendamentos.setData(LocalDate.parse(marcacaoAgendamentos.getData()));
        agendamentos.setHorario(LocalTime.parse(marcacaoAgendamentos.getHorario()));

        agendamentos.setIdCliente(clientesService.getCliente(marcacaoAgendamentos.getIdCliente()));
        agendamentos.setIdAssistencia(assistenciasService.getAssistencia(marcacaoAgendamentos.getIdAssistencia()));
        agendamentos.setIdProduto(produtosService.getProduto(marcacaoAgendamentos.getIdProduto()));

        agendamentosService.postAgendamento(agendamentos);

        return ResponseEntity.created(new URI(Constants.URL+ "agendamentos/" + agendamentos.getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamentos> getAgendamento(@PathVariable Long id){
        Agendamentos agendamentos = agendamentosService.getAgendamento(id);
        return ResponseEntity.ok().body(agendamentos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity.BodyBuilder deletaAgendamento(@PathVariable Long id){
        agendamentosService.deletaAgendamento(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED);
    }

    @GetMapping("/freeslots/{id}")
    public ResponseEntity<List<Slots>> getFreeSlots(@PathVariable Long id){
        List<Slots> freeSlots = agendamentosService.getFreeSlots(id);
        return ResponseEntity.ok().body(freeSlots);
    }

}
