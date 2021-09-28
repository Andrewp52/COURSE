package com.pashenko.eshop.repositories;

import com.pashenko.eshop.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

@Component
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Page<Product> findAll(Pageable pageable);
    Page<Product> findByTitleContaining(String str, Pageable pageable);
}
