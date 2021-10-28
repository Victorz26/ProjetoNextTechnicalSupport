package TechnicalAssistance.DAO;

import TechnicalAssistance.Entities.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientesDAO extends JpaRepository<Clientes, Long> {
}
