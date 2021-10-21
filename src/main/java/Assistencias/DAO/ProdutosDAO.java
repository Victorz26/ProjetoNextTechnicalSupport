package Assistencias.DAO;


import Assistencias.Entities.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutosDAO extends JpaRepository<Produtos, Long> {
}
