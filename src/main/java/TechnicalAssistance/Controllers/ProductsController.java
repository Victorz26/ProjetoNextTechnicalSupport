package TechnicalAssistance.Controllers;

import TechnicalAssistance.Entities.Products;
import TechnicalAssistance.Services.ProductsService;
import TechnicalAssistance.Utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductsService productsService;

    @GetMapping("/")
    public ResponseEntity<List<Products>> getAllProducts() {
        List<Products> products = productsService.getAllProducts();
        return ResponseEntity.ok().body(products);
    }

    @PostMapping("/")
    public ResponseEntity<Void> postProduct(@RequestBody Products products) throws URISyntaxException {
        productsService.postProduct(products);
        return ResponseEntity.created(new URI(Constants.URL+ "products/" + products.getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Products> getProduct(@PathVariable Long id){
        Products products = productsService.getProduct(id);
        return ResponseEntity.ok().body(products);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity.BodyBuilder deleteProduct(@PathVariable Long id){
        productsService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED);
    }
}
