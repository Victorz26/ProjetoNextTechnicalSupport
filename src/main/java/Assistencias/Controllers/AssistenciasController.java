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

    @GetMapping("/all")
    public ResponseEntity<List<Assistencias>> findall() {
        List<Assistencias> assistencias = assistenciasService.findAll();
        return ResponseEntity.ok().body(assistencias);
    }

    @PostMapping("/")
    public ResponseEntity<Void> save(@RequestBody Assistencias assistencias) throws URISyntaxException {
        assistenciasService.salvar(assistencias);
        return ResponseEntity.created(new URI(Constants.URL+ "assistencias/" + assistencias.getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Assistencias> find(@PathVariable Long id){
        Assistencias assistencias = assistenciasService.find(id);
        return ResponseEntity.ok().body(assistencias);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity.BodyBuilder delete(@PathVariable Long id){
        assistenciasService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED);
    }
}
