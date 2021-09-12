package com.pashenko.SpringRest.Repositories;

import com.pashenko.SpringRest.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {

    Product save(Product p);
    void deleteById(long id);

    Optional<Product> findById(long id);
    List<Product> findAll();

    @Query(value = "select min(price) from #{#entityName}", nativeQuery = true)
    int findMinPrice();

    @Query(value = "select max(price) from #{#entityName}", nativeQuery = true)
    int findMaxPrice();

    List<Product> findByPriceAfter(int min);
    List<Product> findByPriceBefore(int max);
    List<Product> findByPriceAfterAndPriceBefore(int min, int max);
}
