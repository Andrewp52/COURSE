package com.pashenko.SpringRest.Controllers;

import com.pashenko.SpringRest.Entities.Product;
import com.pashenko.SpringRest.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("app/products")
    public Product putProduct(@RequestBody Product product){
        return service.saveOrUpdate(product);
    }

    @DeleteMapping("app/products/{id}")
    public int deleteProduct(@PathVariable(name = "id") Long id){
        service.deleteById(id);
        return HttpStatus.OK.value();
    }

    // Simple handler for EmptyResultDataAccessException
    @ExceptionHandler
    public ResponseEntity<String> handleEmptyResult(EmptyResultDataAccessException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
