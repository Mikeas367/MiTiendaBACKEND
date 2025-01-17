package com.backend.backendProyecto.services;

import com.backend.backendProyecto.exceptions.InvalidDataException;
import com.backend.backendProyecto.exceptions.ResourceNotFoundExeption;
import com.backend.backendProyecto.models.Product;
import com.backend.backendProyecto.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(int id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundExeption("product with id: "+id+", not found"));
    }

    public Product findByName(String name) {

        if (!productRepository.existsByName(name)) {
            throw new ResourceNotFoundExeption("product with name: "+name+", not found");
        }
        return productRepository.findByName(name);
    }

    public Product save(Product product) {
        if (productRepository.existsByName(product.getName())) {
            throw new InvalidDataException("product with name: "+product.getName()+", already exists");
        }
        if(!validateData(product)) {
            throw new InvalidDataException("Invalid data");
        }
        return productRepository.save(product);
    }

    public void delete(int id) {
        productRepository.deleteById(id);
    }

    public boolean validateData(Product product) {
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new InvalidDataException("the name of the product cannot be empty");
        }
        String productName = product.getName();

        if ( product.getSalePrice() <= 0) {
            throw new InvalidDataException("Sale price must be greater than 0");
        }

        if (product.getCostPrice() <= 0) {
            throw new InvalidDataException("Cost price must be greater than 0");
        }

        if (product.getStock() < 0) {
            throw new InvalidDataException("Stock cannot be negative");
        }
        return true;
    }

    public Product updateProduct(int id, Product product) {
        // Verificar si el producto existe
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExeption("Product with id " + id + " not found"));

        // Actualizar los datos del producto
        System.out.println("DATOS QUE ME LLEGAN"
                + product.getName()
                + product.getSalePrice()
                + product.getCostPrice()
                + product.getStock());

        existingProduct.setName(product.getName());
        existingProduct.setSalePrice(product.getSalePrice());
        existingProduct.setCostPrice(product.getCostPrice());
        existingProduct.setStock(product.getStock());
        if(!validateData(existingProduct)) {
            throw new InvalidDataException("Invalid data");
        }
        // Guardar los cambios en la base de datos
        return productRepository.save(existingProduct);
    }
}
