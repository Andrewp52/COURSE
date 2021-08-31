package com.pashenko.SpringMVC.service;

import com.pashenko.SpringMVC.DAO.ProductDAO;
import com.pashenko.SpringMVC.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {
    @Autowired
    private ProductDAO dao;

    public void saveOrUpdate(Product product){
        dao.saveOrUpdate(product);
    }

    public Product findById(long id){
        return dao.findById(id);
    }

    public List<Product> findAll(){
        return dao.findAll();
    }

    public void deleteById(long id){
        dao.deleteById(id);
    }
}
