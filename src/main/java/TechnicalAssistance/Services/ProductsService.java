package TechnicalAssistance.Services;

import TechnicalAssistance.DAO.ProductsDAO;
import TechnicalAssistance.Entities.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductsService {
    @Autowired
    private ProductsDAO productsDAO;

    public void postProduct(Products products) {
        productsDAO.save(products);
    }

    public List<Products> getAllProducts() {
        return productsDAO.findAll();
    }

    public Products getProduct(Long id){
        return productsDAO.findById(id).get();
    }

    public void deleteProduct(Long id) {
        productsDAO.deleteById(id);
    }
}
