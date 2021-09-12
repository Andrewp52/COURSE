package com.pashenko.SpringRest.Controllers;

import com.pashenko.SpringRest.Entities.Product;
import com.pashenko.SpringRest.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductControllerRest {
    @Autowired
    private ProductService service;

    @PostMapping("app/products")
    public Product addProduct(@RequestBody Product product){
        return service.saveOrUpdate(product);
    }

    @GetMapping("app/products")
    public List<Product> showAll(@RequestParam(name = "filter", required = false) String filter){
        return filter == null ? service.findAll() : service.findFilteredByPrice(filter);
    }

    @GetMapping("app/products/{id}")
    public Product showById(@PathVariable(name = "id") Long id){
        return service.findById(id);
    }

    @GetMapping("app/products/delete/{id}")
    public String deleteById(@PathVariable(name = "id") Long id){
        try{
            service.deleteById(id);
            return String.format("Product with id: %d deleted", id);
        } catch (EmptyResultDataAccessException e) {
            return String.format("Product with id: %d not found", id);
        }

    }


}
