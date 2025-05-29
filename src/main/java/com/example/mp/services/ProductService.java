package com.example.mp.services;

import com.example.mp.Entitis.Product;
import com.example.mp.repositories.ProductRepository;
import org.springframework.stereotype.Service;


@Service
public class ProductService extends BaseService<Product, Long> {


    public ProductService(ProductRepository productRepository) {
        super(productRepository);
    }


}
