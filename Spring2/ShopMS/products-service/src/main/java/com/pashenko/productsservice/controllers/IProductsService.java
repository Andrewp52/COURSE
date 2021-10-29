package com.pashenko.productsservice.controllers;

import com.pashenko.contract.Product;
import com.pashenko.contract.RestPageImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


public interface IProductsService {
    @GetMapping
    RestPageImpl<Product> getProducts(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "pageSize") int pageSize,
            @RequestParam(value = "word", required = false) String word,
            @RequestParam(value = "min", required = false) Double min,
            @RequestParam(value = "max", required = false) Double max);

    @GetMapping("product")
    Product getProductById(@RequestParam(name = "id") Long id);
}
