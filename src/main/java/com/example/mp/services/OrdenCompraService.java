package com.example.mp.services;

import com.example.mp.Entitis.OrdenCompra;
import com.example.mp.Entitis.Product;
import com.example.mp.repositories.OrdenCompraRepository;
import com.example.mp.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrdenCompraService extends BaseService<OrdenCompra,Long> {

    private final ProductRepository productRepository;
    private final OrdenCompraRepository ordenCompraRepository;

    public OrdenCompraService(OrdenCompraRepository ordenCompraRepository, ProductRepository productRepository) {
        super(ordenCompraRepository);
        this.productRepository = productRepository;
        this.ordenCompraRepository = ordenCompraRepository;
    }

    @Transactional
    public OrdenCompra generarOrdenCompra(List<Long> prodId) throws Exception {
        List<Product> prod = new ArrayList<>();
        Double precioTotal = 0.0;
        System.out.println(prodId);
        for (Long l : prodId) {
            Product d = productRepository.findById(l)
                    .orElseThrow(() -> new Exception("No se encontro el detalle"));
            prod.add(d);
        }

        for (Product d: prod) {
            precioTotal += d.getPrecio();
        }
        OrdenCompra ordenCompra = OrdenCompra.builder()
                .total(precioTotal)
                .fechaCompra(LocalDate.from(LocalDateTime.now()))
                .productlist(prod)
                .build();


        return ordenCompraRepository.save(ordenCompra);
    }
}
