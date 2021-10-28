package TechnicalAssistance.DAO;

import TechnicalAssistance.Entities.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutosDAO extends JpaRepository<Produtos, Long> {
}
