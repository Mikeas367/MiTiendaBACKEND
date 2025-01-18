package com.backend.backendProyecto.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime saleDate;
    private double total;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<SaleDetail> details = new ArrayList<>();

    public List<SaleDetail> getDetails() {
        return details;
    }

    public int getId() {
        return id;
    }

    public void setDetails(List<SaleDetail> details) {
        this.details = details;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public double getTotal() {
        return total;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public void setTotal(double total) {
        this.total = total;
    }


}
