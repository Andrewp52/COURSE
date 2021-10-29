package com.pashenko.productsservice.controllers;

import com.pashenko.contract.Product;
import com.pashenko.contract.RestPageImpl;
import com.pashenko.contract.specifications.ProductSpecs;
import com.pashenko.productsservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProductsServiceImpl implements IProductsService {

    @Autowired
    private ProductService productService;

    @Override
    public RestPageImpl getProducts(@RequestParam(value = "page") int page,
                                                  @RequestParam(value = "pageSize") int pageSize,
                                                  @RequestParam(value = "word", required = false) String word,
                                                  @RequestParam(value = "min", required = false) Double min,
                                                  @RequestParam(value = "max", required = false) Double max) {
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

}
