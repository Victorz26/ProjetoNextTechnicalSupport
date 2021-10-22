package Assistencias.Controllers;

import Assistencias.Entities.Produtos;
import Assistencias.Services.ProdutosService;
import Assistencias.Utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {
    @Autowired
    private ProdutosService produtosService;

    @GetMapping("/all")
    public ResponseEntity<List<Produtos>> findall() {
        List<Produtos> produtos = produtosService.findAll();
        return ResponseEntity.ok().body(produtos);
    }

    @PostMapping("/")
    public ResponseEntity<Void> save(@RequestBody Produtos produtos) throws URISyntaxException {
        produtosService.salvar(produtos);
        return ResponseEntity.created(new URI(Constants.URL+ "produtos/" + produtos.getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produtos> find(@PathVariable Long id){
        Produtos produtos = produtosService.find(id);
        return ResponseEntity.ok().body(produtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity.BodyBuilder delete(@PathVariable Long id){
        produtosService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED);
    }
}
