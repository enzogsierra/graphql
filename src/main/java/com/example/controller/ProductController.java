package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.dto.ProductRequest;
import com.example.model.Category;
import com.example.model.Product;
import com.example.repository.CategoryRepository;
import com.example.repository.ProductRepository;


@Controller
public class ProductController 
{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @QueryMapping
    public List<Product> findAllProducts()
    {
        return productRepository.findAll();
    }

    @QueryMapping
    public Product findProductById(@Argument Long id)
    {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Product ID '%d' not found", id)));
    }


    @MutationMapping
    public Product saveProduct(@Argument ProductRequest productRequest)
    {
        Category category = categoryRepository.findById(productRequest.getCategory_id()).orElse(null);

        Product product = new Product();
        product.setId(productRequest.getId());
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setCategory(category);
        return productRepository.save(product);
    }

    @MutationMapping
    public void deleteProduct(@Argument Long id)
    {
        productRepository.deleteById(id);
    }
}
