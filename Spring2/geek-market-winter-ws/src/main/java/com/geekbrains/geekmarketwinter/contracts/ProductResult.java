package com.geekbrains.geekmarketwinter.contracts;

import com.geekbrains.geekmarketwinter.entites.Product;

import java.io.Serializable;
import java.util.List;

public class ProductResult implements Serializable {
    public List<Product> products;

    public ProductResult(List<Product> products) {
        this.products = products;
    }

    public ProductResult() {
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}