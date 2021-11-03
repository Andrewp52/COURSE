package com.pashenko.productsservice.controllers;

import com.pashenko.contract.Product;
import com.pashenko.contract.ProductDTO;
import com.pashenko.contract.RestPageImpl;
import com.pashenko.contract.specifications.ProductSpecs;
import com.pashenko.productsservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProductsController implements IProductsService {

    @Autowired
    private ProductService productService;

    @Override
    public RestPageImpl getProducts(int page, int pageSize, String word, Double min, Double max) {
        Specification<Product> spec = Specification.where(null);
        if (word != null) {
            spec = spec.and(ProductSpecs.titleContains(word));
        }
        if (min != null) {
            spec = spec.and(ProductSpecs.priceGreaterThanOrEq(min));
        }
        if (max != null) {
            spec = spec.and(ProductSpecs.priceLesserThanOrEq(max));
        }
        Page<Product> products = productService.getProductsWithPagingAndFiltering(page, pageSize, spec);
        return new RestPageImpl(products.toList(), products.getNumber(), pageSize, products.getTotalElements());
    }

    @Override
    public Product getProductById(Long id) {
        return productService.getProductById(id);
    }

    @Override
    public Product addNewProduct(ProductDTO productDTO) {
        return productService.addNewProduct(productDTO);
    }
}
