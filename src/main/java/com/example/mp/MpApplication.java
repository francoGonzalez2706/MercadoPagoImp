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
			productRepository.save(new Product( "Nike Air Force 1",
					"Zapatillas clásicas de cuero blanco con diseño retro", 54999.99));

			productRepository.save(new Product( "Nike Air Max 90",
					"Con unidad Air visible y diseño atemporal", 63999.00));

			productRepository.save(new Product( "Nike Air Max 97",
					"Inspiradas en trenes bala japoneses, con diseño futurista", 71999.50));

			productRepository.save(new Product( "Nike Blazer Mid '77",
					"Estilo retro de básquetbol con swoosh grande", 49999.00));

			productRepository.save(new Product( "Nike Dunk Low",
					"Zapatillas icónicas con colores universitarios", 59999.00));

			productRepository.save(new Product( "Nike React Infinity Run Flyknit",
					"Para correr con máxima amortiguación y estabilidad", 74999.90));

			productRepository.save(new Product( "Nike ZoomX Vaporfly Next%",
					"Zapatillas de élite para competición, ultraligeras", 109999.00));

			productRepository.save(new Product( "Nike Air Zoom Pegasus 40",
					"Zapatillas de running versátiles para todo tipo de corredores", 63999.00));

			productRepository.save(new Product( "Nike Free RN 5.0",
					"Minimalistas y flexibles para sensación natural", 52999.00));

			productRepository.save(new Product( "Nike Air Zoom Structure 24",
					"Estabilidad con amortiguación para entrenamientos diarios", 65999.00));

			productRepository.save(new Product( "Nike SB Dunk Low",
					"Versión skate de las clásicas Dunk con más acolchado", 67999.00));

			productRepository.save(new Product( "Nike Air Huarache",
					"Diseño icónico de los 90 con ajuste tipo media", 59999.00));

			productRepository.save(new Product( "Nike LeBron 21",
					"Zapatillas de básquet de alto rendimiento", 89999.00));

			productRepository.save(new Product( "Nike KD 16",
					"Modelo de Kevin Durant, ligero y con gran tracción", 79999.00));

			productRepository.save(new Product( "Nike Air Jordan 1 Mid",
					"La leyenda continúa con este clásico del básquet", 69999.00));

			productRepository.save(new Product( "Nike Air Jordan 4 Retro",
					"Versión retro con materiales premium y diseño nostálgico", 119999.00));

			productRepository.save(new Product( "Nike Metcon 9",
					"Diseñadas para entrenamiento funcional y levantamiento", 57999.00));

			productRepository.save(new Product( "Nike Zoom Freak 5",
					"Modelo de Giannis Antetokounmpo para agilidad en cancha", 74999.00));

			productRepository.save(new Product( "Nike Air Max Plus",
					"Diseño agresivo con tecnología Tuned Air", 67999.00));
		};
	}
}
