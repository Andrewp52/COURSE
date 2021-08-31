package com.pashenko.SpringBootHW.repository;
import com.pashenko.SpringBootHW.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {

}
