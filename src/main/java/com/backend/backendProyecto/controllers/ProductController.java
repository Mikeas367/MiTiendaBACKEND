package com.backend.backendProyecto.controllers;


import com.backend.backendProyecto.models.Product;
import com.backend.backendProyecto.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/find-id/{id}")
    public Product getProductById(@PathVariable int id) {
        return productService.findById(id);
    }

    @GetMapping("/find-name/{name}")
    public Product getProductByName(@PathVariable String name) {
        return productService.findByName(name);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

}
