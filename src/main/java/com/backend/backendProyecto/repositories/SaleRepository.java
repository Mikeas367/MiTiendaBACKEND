package com.backend.backendProyecto.repositories;

import com.backend.backendProyecto.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SaleRepository extends JpaRepository<Sale, Integer> {
}
