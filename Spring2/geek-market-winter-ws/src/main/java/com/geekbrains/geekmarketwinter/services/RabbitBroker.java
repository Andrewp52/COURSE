package com.geekbrains.geekmarketwinter.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.geekbrains.geekmarketwinter.contracts.GetProducts;
import com.geekbrains.geekmarketwinter.contracts.ProductResult;
import com.geekbrains.geekmarketwinter.entites.Product;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class RabbitBroker {
    private RabbitTemplate rabbitTemplate;

    private final static String QUEUE_NAME = "products-req-queue";

    private final static String QUEUE_RESULT_PRODUCT = "products-resp-queue";

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMsg(int pageNumber, int pageSize) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);

            GetProducts getProducts = new GetProducts(pageNumber, pageSize);

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(getProducts);

            channel.basicPublish("", QUEUE_NAME, null, json.getBytes(StandardCharsets.UTF_8));
        }
    }

    public ProductResult getAndSetMsg() throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_RESULT_PRODUCT, true, false, false, null);
        System.out.println("wait message");

        ObjectMapper mapper = new ObjectMapper();

        ProductResult productResult = null;

        final DeliverCallback deliverCallback = (consumerTag, delivery) -> {

            String msg = new String(delivery.getBody(), "UTF-8");
             mapper.readValue(msg, ProductResult.class);

        };

        String s = channel.basicConsume(QUEUE_RESULT_PRODUCT, true, deliverCallback, consumerTag -> {});
        System.out.println(s);
        return productResult;
    }

}
