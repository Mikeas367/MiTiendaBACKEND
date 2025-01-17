package com.backend.backendProyecto.repositories;

import com.backend.backendProyecto.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByName(String name);
    boolean existsByName(String name);
}
