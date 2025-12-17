package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;


@Entity
@Table(name = "products")
public class Product {
    public Product(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;
    public double price;
    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<BasketItem> basketItems;
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public Long getId(){return id;}
    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }

}

