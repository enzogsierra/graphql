package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.model.Category;
import com.example.repository.CategoryRepository;


@Controller
public class CategoryController 
{
    @Autowired
    private CategoryRepository categoryRepository;


    @QueryMapping
    public List<Category> findAllCategories()
    {
        return categoryRepository.findAll();
    }

    @QueryMapping
    public Category findCategoryById(@Argument Long id)
    {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Category ID '%d' not found", id)));
    }
}
