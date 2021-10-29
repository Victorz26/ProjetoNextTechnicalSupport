package TechnicalAssistance.Controllers;

import TechnicalAssistance.Entities.Customers;
import TechnicalAssistance.Services.CustomersService;
import TechnicalAssistance.Utils.Constants;
import TechnicalAssistance.Validations.ValidateCPF;
import TechnicalAssistance.Validations.ValidateDateTime;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    @Autowired
    private CustomersService customersService;

    @GetMapping("/")
    public ResponseEntity<List<Customers>> getAllCustomers() {
        List<Customers> customers = customersService.getAllCustomers();
        return ResponseEntity.ok().body(customers);
    }

    @PostMapping("/")
    public ResponseEntity<String> postCustomer(@RequestBody Customers customers) throws URISyntaxException {

        if (!ValidateCPF.isCPF(customers.getCpf())) {

            return ResponseEntity.badRequest().body("CPF inválido!");
        }
        try {
            customersService.postCustomer(customers);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("CPF já cadastrado!");
        }

        return ResponseEntity.created(new URI(Constants.URL+ "customers/" + customers.getId())).body("Cliente cadastrado com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customers> getCustomer(@PathVariable Long id){
        Customers customer = customersService.getCustomer(id);
        return ResponseEntity.ok().body(customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity.BodyBuilder deleteCustomer(@PathVariable Long id){
        customersService.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED);
    }

}
