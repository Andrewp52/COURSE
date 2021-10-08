package com.pashenko.eshop.repositories;

import com.pashenko.eshop.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartsRepository extends JpaRepository<Cart, Long> {
    Cart save(Cart cart);
}
