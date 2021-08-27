package com.pashenko.SpringMVC.service;

import com.pashenko.SpringMVC.entity.Product;
import com.pashenko.SpringMVC.repository.ProductRepo;
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
