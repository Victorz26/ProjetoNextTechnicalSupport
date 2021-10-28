package TechnicalAssistance.DAO;

import TechnicalAssistance.Entities.Assistances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssistanceDAO extends JpaRepository<Assistances, Long> {

}
