package TechnicalAssistance.DAO;

import TechnicalAssistance.Entities.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersDAO extends JpaRepository<Customers, Long> {
}
