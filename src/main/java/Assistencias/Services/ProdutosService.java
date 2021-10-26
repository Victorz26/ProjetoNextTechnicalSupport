package Assistencias.Services;

import Assistencias.DAO.ProdutosDAO;
import Assistencias.Entities.Produtos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProdutosService {
    @Autowired
    private ProdutosDAO produtosDAO;

    public void postProduto(Produtos produtos) {
        produtosDAO.save(produtos);
    }

    public List<Produtos> getTodosProdutos() {
        return produtosDAO.findAll();
    }

    public Produtos getProduto(Long id){
        return produtosDAO.findById(id).get();
    }

    public void deletaProduto(Long id) {
        produtosDAO.deleteById(id);
    }
}
