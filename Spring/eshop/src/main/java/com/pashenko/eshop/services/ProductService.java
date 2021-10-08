package com.pashenko.eshop.services;

import com.pashenko.eshop.entities.Product;
import com.pashenko.eshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;


@Component
public class ProductService {
    @Autowired
    ProductRepository repository;

    public Product findById(Long id){
        return repository.findById(id).get();
    }
    public Page<Product> findAll(Integer size, Integer page) {
        return repository.findAll(Pageable.ofSize(size).withPage(page));
    }

    public Page<Product> findByTitle(String title, int pageSize, Integer page){
        return repository.findByTitleContaining(title, Pageable.ofSize(pageSize).withPage(page));
    }
}
