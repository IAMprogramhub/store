package com.example.demo.dto;

import lombok.Data;


public class BasketItemDto {
    private Long id;
    private String productName;
    private double productPrice;
    private int quantity;

    public BasketItemDto(Long id, String productName, double productPrice, int quantity) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }
    public Long getId(){return id;}
    public String getProductName(){return productName;}
    public double getProductPrice() {return productPrice;}
    public int getQuantity(){return quantity;}

    public void setId(Long id){this.id=id;}
    public void setProductName(String productName){this.productName=productName;}
    public void setProductPrice(double productPrice){this.productPrice=productPrice;}
    public void setQuantity(int quantity){this.quantity=quantity;}
}
