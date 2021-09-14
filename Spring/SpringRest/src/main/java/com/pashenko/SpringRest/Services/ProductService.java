package com.pashenko.SpringRest.Services;

import com.pashenko.SpringRest.Entities.Product;
import com.pashenko.SpringRest.Repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductService {
    @Autowired
    private ProductRepo repo;

    public Product saveOrUpdate(Product p){
        return repo.save(p);
    }

    public void deleteById(long id){
        repo.deleteById(id);
    }

    public List<Product> findAll(){
        return repo.findAll();
    }

    public Product findById(long id){
        return repo.findById(id).orElseGet(() -> {return null;});
    }

    public List<Product> findFilteredByPrice(String filter){
        List<Product> res;
        switch (filter){
            case "expMin" : res = repo.findByPriceAfter(repo.findMinPrice()); break;
            case "chpMax" : res = repo.findByPriceBefore(repo.findMaxPrice()); break;
            case "betwMinMax" : res = repo.findByPriceAfterAndPriceBefore(repo.findMinPrice(), repo.findMaxPrice()); break;
            default : res = new ArrayList<>();
        }
        return res;
    }

}
