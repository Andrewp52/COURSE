package com.pashenko.wintermarket.services;

import com.pashenko.contract.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(contextId = "categories", name = "products-service")
public interface ICategoriesService {
    @GetMapping("/categories")
    List<Category> getAllCategories();
}
