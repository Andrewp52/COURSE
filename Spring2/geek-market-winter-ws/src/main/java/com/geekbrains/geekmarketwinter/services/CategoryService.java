package com.geekbrains.geekmarketwinter.services;

import com.geekbrains.geekmarketwinter.entites.Category;
import com.geekbrains.geekmarketwinter.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private RabbitBroker rabbitBroker;

    public List<Category> getAllCategories() {
        return rabbitBroker.getAllCategories();
    }
}
