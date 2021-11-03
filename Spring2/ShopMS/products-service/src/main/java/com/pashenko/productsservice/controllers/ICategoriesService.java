package com.pashenko.productsservice.controllers;

import com.pashenko.contract.Category;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface ICategoriesService {
    @GetMapping("/categories")
    List<Category> getAllCategories();
}
