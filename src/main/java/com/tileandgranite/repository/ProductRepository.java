package com.tileandgranite.repository;

import com.tileandgranite.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductCode(String productCode);
    java.util.List<Product> findByNameContainingIgnoreCase(String name);
    java.util.List<Product> findByType(String type);
}
