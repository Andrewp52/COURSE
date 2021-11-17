//package com.geekbrains.geekmarketwinter.services;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectWriter;
//import com.geekbrains.geekmarketwinter.contract.GetProducts;
//import com.geekbrains.geekmarketwinter.contract.ProductResult;
//import com.geekbrains.geekmarketwinter.entites.Product;
//import com.geekbrains.geekmarketwinter.repositories.ProductRepository;
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.Connection;
//import com.rabbitmq.client.ConnectionFactory;
//import com.rabbitmq.client.DeliverCallback;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.stereotype.Service;
//
//import java.nio.charset.StandardCharsets;
//import java.util.List;
//
//
//public class RabbitBroker {
//
//    private final static String QUEUE_GET_PRODUCT = "get-product-queue";
//
//    private final static String QUEUE_RESULT_PRODUCT = "result-product-queue";
//
//    private ProductRepository productRepository;
//
//    @Autowired
//    public void setProductRepository(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }
//
//    public void getAndSetMsg() throws Exception  {
//
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//        channel.queueDeclare(QUEUE_GET_PRODUCT, true, false, false, null);
//        System.out.println("wait message");
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        final DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//
//            String msg = new String(delivery.getBody(), "UTF-8");
//            GetProducts products = mapper.readValue(msg, GetProducts.class);
//
//            Specification<Product> spec = Specification.where(null);
//            Page<Product> productsResult = productRepository.findAll(spec, PageRequest.of(products.getPageNumber(), products.getPageSize()));
//
//            List<Product> productResult = productsResult.getContent();
//
////            ProductResult rs = new ProductResult(productResult);
//
//
//            channel.queueDeclare(QUEUE_RESULT_PRODUCT, true, false, false, null);
//
//
//            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//            String json = ow.writeValueAsString(rs);
//
//            channel.basicPublish("", QUEUE_RESULT_PRODUCT, null, json.getBytes(StandardCharsets.UTF_8));
//
//            System.out.println("Reciver " + msg);
//        };
//
//        channel.basicConsume(QUEUE_GET_PRODUCT, true, deliverCallback, consumerTag -> {});
//
//    }
//
//
//}
