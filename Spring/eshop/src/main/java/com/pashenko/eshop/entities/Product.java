package com.pashenko.eshop.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    String title;

    @Column
    String shortDescription;

    @Column
    String fullDescription;

    @Column
    BigDecimal price;

    @Column
    Integer quantity;

    // Equals & hashCode watch "Id" field only !!!
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getId().equals(product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
