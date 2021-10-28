package TechnicalAssistance.Services;

import TechnicalAssistance.DAO.CustomersDAO;
import TechnicalAssistance.Entities.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomersService {
    @Autowired
    private CustomersDAO customersDAO;

    public void postCustomer(Customers customers) {
        customersDAO.save(customers);
    }

    public List<Customers> getAllCustomers() {
        return customersDAO.findAll();
    }

    public Customers getCustomer(Long id){
        return customersDAO.findById(id).get();
    }

    public void deleteCustomer(Long id) {
        customersDAO.deleteById(id);
    }
}
