package com.example.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class ProductRequest 
{
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private Long category_id;
}
