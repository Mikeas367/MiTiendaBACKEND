package com.backend.backendProyecto.services;

import com.backend.backendProyecto.dtos.SaleDTO;
import com.backend.backendProyecto.dtos.SaleDetailDTO;
import com.backend.backendProyecto.exceptions.InvalidDataException;
import com.backend.backendProyecto.exceptions.ResourceNotFoundExeption;
import com.backend.backendProyecto.models.Product;
import com.backend.backendProyecto.models.Sale;
import com.backend.backendProyecto.models.SaleDetail;
import com.backend.backendProyecto.repositories.ProductRepository;
import com.backend.backendProyecto.repositories.SaleDetailRepository;
import com.backend.backendProyecto.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SaleDetailRepository saleDetailRepository;

    public List<SaleDTO> getAllSales() {
        return saleRepository.findAll().stream().map(sale -> {
            SaleDTO saleDTO = new SaleDTO();
            saleDTO.setId(sale.getId());
            saleDTO.setDate(sale.getSaleDate());
            saleDTO.setDetails(
                    sale.getDetails().stream().map(detail -> {
                        SaleDetailDTO detailDTO = new SaleDetailDTO();
                        detailDTO.setProductName(detail.getProductName());
                        detailDTO.setProductPrice(detail.getProductSalePrice());
                        detailDTO.setQuantity(detail.getQuantity());
                        return detailDTO;
                    }).collect(Collectors.toList())
            );
            return saleDTO;
        }).collect(Collectors.toList());
    }

    public Sale getSaleById(int id) {
        return saleRepository.findById(id)
                .orElseThrow(()-> new ResolutionException(" sale with id: " + id +" not found"));
    }

    public Sale addSale(Sale sale) {
        for (SaleDetail detail : sale.getDetails() ) {
            // obtengo el producto del detalle
            //Product product = productRepository.findById(detail.getProduct().getId())
            //        .orElseThrow(() -> new ResourceNotFoundExeption("Product not found"));

            if (!productRepository.existsByName(detail.getProductName())){
                throw new ResourceNotFoundExeption("product with name: " + detail.getProductName() + " not found");
            }
            Product product = productRepository.findByName(detail.getProductName());

            // veo el stock
            if (product.getStock() < detail.getQuantity()) {
                throw new InvalidDataException("Insufficient stock for product: " + product.getName());
            }

            product.setStock(product.getStock() - detail.getQuantity());
            productRepository.save(product); // guardo el stock

            detail.setProductName(product.getName());
            detail.setProductSalePrice(product.getSalePrice());
            detail.setPrice(product.getSalePrice() * detail.getQuantity());
            detail.setSale(sale);


        }
        sale.setSaleDate(LocalDateTime.now());
        sale.setTotal(sale.getDetails().stream()
                .mapToDouble(detail -> detail.getPrice() * detail.getQuantity())
                .sum());


        return saleRepository.save(sale);

    }

    public List<Map<String, Object>> getTopSellingProducts() {
        return saleDetailRepository.findTopSellingProducts()
                .stream()
                .map(result -> Map.of(
                        "productName", result[0],       // Índice 0: Nombre del producto
                        "totalQuantity", result[1]      // Índice 1: Cantidad total
                ))
                .collect(Collectors.toList());
    }

    public List<Map<String, Object>> getRevenueByProduct() {
        return saleDetailRepository.findTotalRevenueByProduct()
                .stream()
                .map(result -> Map.of(
                        "productName", result[0],       // Índice 0: Nombre del producto
                        "totalRevenue", result[1]       // Índice 1: Ingresos totales
                ))
                .collect(Collectors.toList());
    }
}
