package TechnicalAssistance.Controllers;

import TechnicalAssistance.Entities.Assistances;
import TechnicalAssistance.Services.AssistancesService;
import TechnicalAssistance.Utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/assistances")
public class AssistancesController {
    @Autowired
    private AssistancesService assistancesService;

    @GetMapping("/")
    public ResponseEntity<List<Assistances>> getAllAssistances() {
        List<Assistances> assistances = assistancesService.getAllAssistances();
        return ResponseEntity.ok().body(assistances);
    }

    @PostMapping("/")
    public ResponseEntity<Void> postAssistance(@RequestBody Assistances assistances) throws URISyntaxException {
        assistancesService.postAssistance(assistances);
        return ResponseEntity.created(new URI(Constants.URL+ "assistances/" + assistances.getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Assistances> getAssistance(@PathVariable Long id){
        Assistances assistances = assistancesService.getAssistance(id);
        return ResponseEntity.ok().body(assistances);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity.BodyBuilder deleteAssistance(@PathVariable Long id){
        assistancesService.deleteAssistance(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED);
    }
}
