package com.geekbrains.geekmarketwinter.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.geekbrains.geekmarketwinter.JsonConverter;
import com.geekbrains.geekmarketwinter.entites.Category;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class CategoriesRabbitRPC {
    @Autowired
    CategoryService categoryService;

    JsonConverter converter = new JsonConverter();

    // Works when receives any message from queue
    @RabbitListener(queues = "categories-req-queue")
    public String acceptMessage(Message message) throws IOException {
        return getJsonStringFromList(getAllCategories());
    }

    private List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    private String getJsonStringFromList(List<Category> categories) throws JsonProcessingException {
        return converter.objectToString(categories);
    }
}
