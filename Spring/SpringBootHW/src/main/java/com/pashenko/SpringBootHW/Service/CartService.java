package com.pashenko.SpringBootHW.Service;

import com.pashenko.SpringBootHW.entity.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartService {
    // cart map (Product / Quantity)
    private Map<Product, Integer> cart = new HashMap<>();

    // adds selected product to the cart
    // if such product is exist - updates quantity
    public void add(@NonNull Product product){
        int quantity = cart.getOrDefault(product, 0);
        if(quantity == 0){
            this.cart.put(product, 1);
        } else {
            this.cart.put(product, quantity + 1);
        }
    }

    // adds quantity into position with given key
    public void addQuant(@NonNull Product product){
        this.cart.compute(product, (k, v) -> v + 1);
    }

    // withdraws quantity from position with given key
    // if quantity is 1 - removes product from the cart
    public void remQuant(@NonNull Product product){
        if(this.cart.get(product).equals(1)){
            this.cart.remove(product);
        } else {
            this.cart.compute(product, (k, v) -> v - 1);
        }
    }

    // calculates total cart price
    public int getTotalPrice(){
        AtomicInteger total = new AtomicInteger();
        cart.forEach((product, quantity) -> total.getAndAdd(product.getPrice() * quantity));
        return total.get();
    }

    public Map<Product, Integer> getCart() {
        return this.cart;
    }
}
