package com.geekbrains.geekmarketwinter.services;

import com.geekbrains.geekmarketwinter.contracts.GetProducts;
import com.geekbrains.geekmarketwinter.contracts.ProductResult;
import com.geekbrains.geekmarketwinter.entites.Category;
import com.geekbrains.geekmarketwinter.entites.Product;
import com.geekbrains.geekmarketwinter.utils.JsonConverter;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class RabbitBroker {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    @Qualifier(value = "prod-request")
    private Queue productsReqQueue;

    @Autowired
    @Qualifier(value = "cat-request")
    private Queue categoriesReqQueue;

    JsonConverter converter = new JsonConverter();

    // Requests products page from service via RPC
    public Page<Product> getAllProductsByPage(int pageNumber, int pageSize) throws IOException {
        String json = converter.objectToString(new GetProducts(pageNumber, pageSize));
        String resp = (String) rabbitTemplate.convertSendAndReceive(productsReqQueue.getName(), json);
        ProductResult result = (ProductResult) converter.bytesToObject(resp.getBytes(StandardCharsets.UTF_8), ProductResult.class);
        return result.getProducts();
    }

    public List<Category> getAllCategories() {
        List<Category> result = null;
        try {
            String resp = (String) rabbitTemplate.convertSendAndReceive(categoriesReqQueue.getName(), converter.objectToString("req"));
            result = (List<Category>) converter.bytesToObject(resp.getBytes(StandardCharsets.UTF_8), List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
