package com.geekbrains.geekmarketwinter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geekbrains.geekmarketwinter.contract.GetProducts;
import com.geekbrains.geekmarketwinter.contract.JSONPageImpl;
import com.geekbrains.geekmarketwinter.contract.ProductResult;
import com.geekbrains.geekmarketwinter.entites.Product;
import com.geekbrains.geekmarketwinter.services.ProductService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RabbitReqReceiver {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    ProductService productService;

    ObjectMapper mapper = new ObjectMapper();

    @RabbitListener(queues = "products-req-queue")
    public void acceptMessage(Message message) throws IOException {
        String msg = new String(message.getBody(), "UTF-8");
        GetProducts request = mapper.readValue(msg, GetProducts.class);
        respond(getPage(request));
    }

    private Page<Product> getPage(GetProducts request){
        return productService.getAllProductsByPage(request.pageNumber, request.pageSize);
    }

    private void respond(Page<Product> page) throws JsonProcessingException {
        String response = packDataToJsonString(page);
        rabbitTemplate.convertAndSend("products-resp-queue", response);
    }

    private String packDataToJsonString(Page<Product> products) throws JsonProcessingException {
        ProductResult result = new ProductResult(new JSONPageImpl<>(
                products.getContent(),
                products.getNumber(),
                products.getSize(),
                products.getTotalElements())
        );
        return mapper.writer().writeValueAsString(result);
    }
}
