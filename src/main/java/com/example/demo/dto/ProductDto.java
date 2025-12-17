package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {
    public ProductDto(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    private Long id;
    public String name;
    private double price;
    public Long getId(){return id;}
    public String getName(){return name;}
    public double getPrice(){return price;}
    public void setId(Long id){this.id=id;}
    public void setName(String name){this.name=name;}
    public void setPrice(double price){this.price=price;}







}
