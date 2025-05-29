package com.example.mp.repositories;

import com.example.mp.Entitis.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long> { }
