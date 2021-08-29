package com.pashenko.SpringBootHW.service;


import com.pashenko.SpringBootHW.entity.Product;
import com.pashenko.SpringBootHW.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {
    @Autowired
    private ProductRepo repo;

    public void addProduct(Product product){
        repo.addNewProduct(product);
    }

    public Product geyById(int id){
        return repo.getProductById(id);
    }

    public List<Product> getAll(){
        return repo.getAllProduct();
    }
}
