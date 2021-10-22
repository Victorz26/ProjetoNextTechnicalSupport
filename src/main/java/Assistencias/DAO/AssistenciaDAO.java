package Assistencias.DAO;


import Assistencias.Entities.Assistencias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssistenciaDAO extends JpaRepository<Assistencias, Long> {

}
