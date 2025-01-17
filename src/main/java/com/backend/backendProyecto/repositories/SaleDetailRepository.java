package com.backend.backendProyecto.repositories;

import com.backend.backendProyecto.models.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SaleDetailRepository extends JpaRepository<SaleDetail, Integer> {

    @Query("SELECT p.id, SUM(sd.quantity), p.name FROM SaleDetail sd " +
            "JOIN sd.product p " +
            "GROUP BY p.id ORDER BY SUM(sd.quantity) DESC")
    List<Object[]> findTopSellingProducts();

    @Query("SELECT p.id, SUM(sd.quantity * sd.price), p.name FROM SaleDetail sd " +
            "JOIN sd.product p " +
            "GROUP BY p.id ORDER BY SUM(sd.quantity * sd.price) DESC")
    List<Object[]> findTotalRevenueByProduct();

}
