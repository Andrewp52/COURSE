package com.geekbrains.geekmarketwinter.contract;

import com.geekbrains.geekmarketwinter.entites.Product;

import java.io.Serializable;
import java.util.List;

public class ProductResult implements Serializable {
    JSONPageImpl<Product> products;

    public ProductResult(JSONPageImpl<Product> products) {
        this.products = products;
    }

    public ProductResult() {
    }

    public JSONPageImpl<Product> getProducts() {
        return products;
    }

    public void setProducts(JSONPageImpl<Product> products) {
        this.products = products;
    }
}
