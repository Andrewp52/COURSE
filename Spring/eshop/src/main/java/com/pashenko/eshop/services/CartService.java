package com.pashenko.eshop.services;

import com.pashenko.eshop.entities.Cart;
import com.pashenko.eshop.entities.Product;
import com.pashenko.eshop.repositories.CartsRepository;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Map;

@Component
@Data
public class CartService {
@Autowired
    CartsRepository cartsRepository;

    public void add(Product product, HttpSession session) {
      getCart(session).add(product);
    }

    public void rem(Product product, HttpSession session){
        getCart(session).rem(product);
    }

    public BigDecimal getTotalPrice(HttpSession session){
        return getCart(session).getTotalPrice();
    }

    public Map<Product, Integer> getProducts(HttpSession session) {
        return getCart(session).getProducts();
    }

    public void save(HttpSession session) {
        cartsRepository.save(getCart(session));
    }

    // Retrieves cart from session
    // If cart is not exist, creates new and put it to the session
    public Cart getCart(HttpSession session){
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart != null){
            return cart;
        }
        cart = new Cart();
        session.setAttribute("cart", cart);
        return cart;
    }

}
