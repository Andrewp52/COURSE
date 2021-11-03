package com.pashenko.productsservice.controllers;

import com.pashenko.contract.Category;
import com.pashenko.productsservice.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoriesController implements ICategoriesService{
    @Autowired
    CategoryService categoryService;

    @Override
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
