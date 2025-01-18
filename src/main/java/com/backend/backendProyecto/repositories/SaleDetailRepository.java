package com.backend.backendProyecto.repositories;

import com.backend.backendProyecto.models.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SaleDetailRepository extends JpaRepository<SaleDetail, Integer> {

    @Query("SELECT sd.productName, SUM(sd.quantity) AS totalQuantity " +
            "FROM SaleDetail sd " +
            "GROUP BY sd.productName " +
            "ORDER BY totalQuantity DESC")
    List<Object[]> findTopSellingProducts();

    @Query("SELECT sd.productName, SUM(sd.quantity * sd.productSalePrice) AS totalRevenue " +
            "FROM SaleDetail sd " +
            "GROUP BY sd.productName " +
            "ORDER BY totalRevenue DESC")
    List<Object[]> findTotalRevenueByProduct();

}
