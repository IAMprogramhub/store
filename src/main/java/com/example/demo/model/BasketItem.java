package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class BasketItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;
    private int quantity;

    public BasketItem() {}

    public BasketItem(User user, Product product, int quantity) {
        this.user = user;
        this.product = product;
        this.quantity = quantity;
    }
    public Long getId(){return id;}
    public User getUser(){return user;}
    public Product getProduct(){return product;}
    public int getQuantity(){return quantity;}

    public void setUser(User user){this.user=user;}
    public void setProduct(Product product){this.product=product;}
    public void setQuantity(int quantity){this.quantity=quantity;}


}
