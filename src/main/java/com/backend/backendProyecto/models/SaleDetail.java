package com.backend.backendProyecto.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


@Entity
public class SaleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    @JsonBackReference
    private Sale sale;

    //@ManyToOne
    //@JoinColumn(name = "product_id")
    //private Product product;

    private int quantity;
    private double price;

    private String productName;
    private double productSalePrice;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductSalePrice() {
        return productSalePrice;
    }

    public void setProductSalePrice(double productSalePrice) {
        this.productSalePrice = productSalePrice;
    }

    public int getId() {
        return id;
    }

    public Sale getSale() {
        return sale;
    }


    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }



    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
