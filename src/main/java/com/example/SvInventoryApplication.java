package com.example;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.model.Category;
import com.example.model.Product;
import com.example.repository.CategoryRepository;
import com.example.repository.ProductRepository;

@SpringBootApplication
public class SvInventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SvInventoryApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ProductRepository productRepository, CategoryRepository categoryRepository)
	{
		return args ->
		{
			Random random = new Random();

			List.of("PC Gamer", "Printers", "Smartphones").forEach(item ->
			{
				Category category = Category.builder().name(item).build();
				categoryRepository.save(category);
			});

			categoryRepository.findAll().forEach(category ->
			{
				for(int i = 0; i < 10; i++)
				{
					Product product = 
						Product.builder()
							.name(category.getName() + " #" + i)
							.price(new BigDecimal(1000.0 + (Math.random() * 5000)))
							.stock(random.nextInt(100) + 1)
							.category(category)
							.build();

					productRepository.save(product);
				}
			});
		};
	}
}
