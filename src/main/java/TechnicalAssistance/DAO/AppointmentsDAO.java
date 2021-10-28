package TechnicalAssistance.DAO;

import TechnicalAssistance.Entities.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentsDAO extends JpaRepository<Appointments, Long> {

}
