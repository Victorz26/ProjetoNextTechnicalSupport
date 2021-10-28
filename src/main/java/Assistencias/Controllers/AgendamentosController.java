package Assistencias.Controllers;

import Assistencias.Entities.Agendamentos;
import Assistencias.Entities.MarcacaoAgendamentos;
import Assistencias.Services.AgendamentosService;
import Assistencias.Services.AssistenciasService;
import Assistencias.Services.ClientesService;
import Assistencias.Services.ProdutosService;
import Assistencias.Utils.Constants;
import Assistencias.Validacao.ValidacaoGarantia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
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

    @PostMapping({"/", ""})
    public ResponseEntity<Void> postAgendamentos(@RequestBody MarcacaoAgendamentos marcacaoAgendamentos) throws URISyntaxException {
        boolean estaCerto  = true;
        if(!ValidacaoGarantia.validaGarantia(marcacaoAgendamentos)){
            estaCerto = false;
            return ResponseEntity.badRequest().build();
        }

        if(estaCerto) {
            Agendamentos agendamentos = new Agendamentos();

            agendamentos.setData(marcacaoAgendamentos.getData());
            agendamentos.setHorario(marcacaoAgendamentos.getHorario());
            //agendamentos.setDataDaCompra(marcacaoAgendamentos.getDataDaCompra());

            agendamentos.setIdCliente(clientesService.getCliente(marcacaoAgendamentos.getIdCliente()));
            agendamentos.setIdAssistencia(assistenciasService.getAssistencia(marcacaoAgendamentos.getIdAssistencia()));
            agendamentos.setIdProduto(produtosService.getProduto(marcacaoAgendamentos.getIdProduto()));


            agendamentosService.postAgendamento(agendamentos);

            return ResponseEntity.created(new URI(Constants.URL + "agendamentos/" + agendamentos.getId())).build();
        }
        return ResponseEntity.badRequest().build();
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

}
