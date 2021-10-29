package com.pashenko.wintermarket.services;

import com.pashenko.contract.Product;
import com.pashenko.contract.RestPageImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("products-service")
public interface IProductsService {
    @GetMapping
    RestPageImpl<Product> getProducts(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "pageSize") int pageSize,
            @RequestParam(value = "word") String word,
            @RequestParam(value = "min") Double min,
            @RequestParam(value = "max") Double max);
}
