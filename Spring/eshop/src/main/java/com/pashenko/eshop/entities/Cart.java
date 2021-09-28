package com.pashenko.eshop.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Entity(name = "carts")
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @JoinTable(name = "carts_items")
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "quantity")
    private Map<Product,Integer> products = new HashMap<>();

    // Adds products to the cart
    // if the product is already in cart, changes it`s quantity
    public void add(Product product) {
        int quant = this.products.getOrDefault(product, 0);
        if(quant == 0){
            this.products.put(product, 1);
        } else {
            this.products.compute(product, (k, v) -> v + 1);
        }
    }

    // Removes products from cart (if quantity is 1)
    // If product`s quantity > 1, changes it`s quantity
    public void rem(Product product){
        if(this.products.get(product) == 1){
            this.products.remove(product);
        } else {
            this.products.compute(product, (k, v) -> v - 1);
        }
    }

    // Calculates whole cart price
    public BigDecimal getTotalPrice(){
        AtomicReference<BigDecimal> sum = new AtomicReference<>();
        sum.set(BigDecimal.ZERO);
        this.products.forEach((k, v) -> {
            BigDecimal posPrice = k.getPrice().multiply(BigDecimal.valueOf(v));
            sum.set(sum.get().add(posPrice));
        });
        return sum.get();
    }


}
