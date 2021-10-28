package TechnicalAssistance.DAO;

import TechnicalAssistance.Entities.Agendamentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentosDAO extends JpaRepository<Agendamentos, Long> {

}
