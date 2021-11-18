package com.geekbrains.geekmarketwinter.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.geekbrains.geekmarketwinter.JsonConverter;
import com.geekbrains.geekmarketwinter.contract.GetProducts;
import com.geekbrains.geekmarketwinter.contract.JSONPageImpl;
import com.geekbrains.geekmarketwinter.contract.ProductResult;
import com.geekbrains.geekmarketwinter.entites.Product;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProductsRabbitRPC {
    @Autowired
    ProductService productService;

    JsonConverter converter = new JsonConverter();

    @RabbitListener(queues = "products-req-queue")
    public String acceptMessage(Message message) throws IOException {
        GetProducts request = (GetProducts) converter.bytesToObject(message.getBody(), GetProducts.class);
        return getJsonStringFromPage(getPage(request));
    }

    private Page<Product> getPage(GetProducts request){
        return productService.getAllProductsByPage(request.pageNumber, request.pageSize);
    }

    private String getJsonStringFromPage(Page<Product> products) throws JsonProcessingException {
        ProductResult result = new ProductResult(new JSONPageImpl<>(
                products.getContent(),
                products.getNumber(),
                products.getSize(),
                products.getTotalElements())
        );
        return converter.objectToString(result);
    }
}
