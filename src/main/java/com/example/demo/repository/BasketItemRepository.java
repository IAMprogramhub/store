package com.example.demo.repository;

import com.example.demo.model.BasketItem;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BasketItemRepository extends JpaRepository<BasketItem, Long> {
    List<BasketItem> findByUser(User user);
    List<BasketItem> findByUserAndProduct(User user, Product product);


}
