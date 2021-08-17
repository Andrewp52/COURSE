package com.pashenko.lesson1.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Product {
    private static int count;
    private final int id;
    private String title;
    private BigDecimal cost;

    public Product(String title, BigDecimal cost) {
        this.id = count++;
        this.title = title;
        this.cost = cost;
    }

    public static List<Product> generate(int quant){
        Random r = new Random();
        List<Product> res = new ArrayList<>(quant);
        for (int i = 0; i < quant; i++) {
            res.add(new Product(
                    "Prod - " + (count),
                    BigDecimal.valueOf(r.nextDouble() * 100).setScale(2, RoundingMode.HALF_UP)
                    )
            );
        }
        return res;
    }

    @Override
    public String toString() {
        return "ID# " + this.id + " Title: " + title + " - " + "Cost " + cost;
    }
}
