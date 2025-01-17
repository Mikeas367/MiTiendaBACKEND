package com.backend.backendProyecto.controllers;

import com.backend.backendProyecto.dtos.SaleDTO;
import com.backend.backendProyecto.models.Sale;
import com.backend.backendProyecto.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sales")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @GetMapping
    public List<SaleDTO> getAllSales() {
        return saleService.getAllSales();
    }

    @GetMapping("/{id}")
    public Sale getSaleById(@PathVariable int id) {
        return saleService.getSaleById(id);
    }

    @PostMapping()
    public Sale addSale(@RequestBody Sale sale) {
        return saleService.addSale(sale);
    }

    @GetMapping("/reports/top-selling-products")
    public List<Map<String, Object>> getTopSellingProducts() {
        return saleService.getTopSellingProducts();
    }

    @GetMapping("/reports/revenue-by-product")
    public List<Map<String, Object>> getRevenueByProduct() {
        return saleService.getRevenueByProduct();
    }
}
