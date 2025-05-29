package com.example.mp;

import com.example.mp.Entitis.Product;
import com.example.mp.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MpApplication {

	public static void main(String[] args) {
		SpringApplication.run(MpApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(ProductRepository productRepository) {
		return args -> {
			for (int i = 1; i <= 20; i++) {
				Product producto = new Product();
				producto.setNombre("Producto " + i);
				producto.setDescripcion("Descripción del producto número " + i);
				producto.setPrecio(1000.0 + i * 100); // Precio base + incremento por índice

				productRepository.save(producto);
			}
		};
	}
}
