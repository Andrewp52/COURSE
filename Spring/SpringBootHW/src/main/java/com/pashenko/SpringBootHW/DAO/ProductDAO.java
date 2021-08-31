package com.pashenko.SpringBootHW.DAO;

import com.pashenko.SpringBootHW.entity.Product;
import com.pashenko.SpringBootHW.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDAO {
    @Autowired
    private ProductRepo repo;

    public void saveOrUpdate(Product product){
        repo.save(product);
    }

    public Product findById(long id){
        return repo.findById(id).orElse(null);
    }

    public List<Product> findAll(){
        return repo.findAll();
    }

    public void deleteById(long id){
        repo.deleteById(id);
    }

}
