package com.geekbrains.geekmarketwinter.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.geekbrains.geekmarketwinter.contracts.GetProducts;
import com.geekbrains.geekmarketwinter.contracts.JSONPageImpl;
import com.geekbrains.geekmarketwinter.contracts.ProductResult;
import com.geekbrains.geekmarketwinter.entites.Product;
import com.geekbrains.geekmarketwinter.repositories.ProductRepository;
import com.geekbrains.geekmarketwinter.repositories.specifications.ProductSpecs;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return (List<Product>)(productRepository.findAll());
    }

    public List<Product> getAllProductsWithFilter(Specification<Product> productSpecs) {
        return (List<Product>)(productRepository.findAll(productSpecs));
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Page<Product> getAllProductsByPage(int pageNumber, int pageSize) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String json = mapper.writeValueAsString(new GetProducts(pageNumber, pageSize));

        rabbitTemplate.convertAndSend("products-req-queue", json);
        Message response = rabbitTemplate.receive("products-resp-queue", 3000);
        ProductResult result = mapper.readValue(response.getBody(), ProductResult.class);
        JSONPageImpl<Product> page = result.getProducts();
        return page;

    }

    public Page<Product> getProductsWithPagingAndFiltering(int pageNumber, int pageSize, Specification<Product> productSpecification) {
        return productRepository.findAll(productSpecification, PageRequest.of(pageNumber, pageSize));
    }

    public boolean isProductWithTitleExists(String productTitle) {
        return productRepository.findOneByTitle(productTitle) != null;
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }
}
