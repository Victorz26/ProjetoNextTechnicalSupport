package Assistencias.Controllers;

import Assistencias.Entities.Assistencias;
import Assistencias.Services.AssistenciasService;
import Assistencias.Utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/assistencias")
public class AssistenciasController {
    @Autowired
    private AssistenciasService assistenciasService;

    @GetMapping("/")
    public ResponseEntity<List<Assistencias>> getTodasAssistencias() {
        List<Assistencias> assistencias = assistenciasService.getTodosAssistencias();
        return ResponseEntity.ok().body(assistencias);
    }

    @PostMapping("/")
    public ResponseEntity<Void> postAssistencia(@RequestBody Assistencias assistencias) throws URISyntaxException {
        assistenciasService.postAssistencia(assistencias);
        return ResponseEntity.created(new URI(Constants.URL+ "assistencias/" + assistencias.getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Assistencias> getAssistencia(@PathVariable Long id){
        Assistencias assistencias = assistenciasService.getAssistencia(id);
        return ResponseEntity.ok().body(assistencias);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity.BodyBuilder deletaAssistencia(@PathVariable Long id){
        assistenciasService.deletaAssistencia(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED);
    }
}
