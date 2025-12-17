package com.example.demo.controller;

import com.example.demo.dto.BasketItemDto;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/basket")
public class BasketController {

    private final BasketItemRepository basketItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public BasketController(BasketItemRepository basketItemRepository,
                            UserRepository userRepository,
                            ProductRepository productRepository) {
        this.basketItemRepository = basketItemRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addToBasket(
            @RequestParam Long userId,
            @RequestParam Long productId,
            @RequestParam(defaultValue = "1") int quantity) {

        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Product> productOpt = productRepository.findById(productId);

        if (userOpt.isEmpty() || productOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User or Product not found");
        }

        User user = userOpt.get();
        Product product = productOpt.get();


        List<BasketItem> items = basketItemRepository.findByUserAndProduct(user, product);
        if (!items.isEmpty()) {
            BasketItem item = items.get(0);
            item.setQuantity(item.getQuantity() + quantity);
            basketItemRepository.save(item);
                return ResponseEntity.ok("Product exist quantity + " + quantity);
        } else {
            BasketItem item = new BasketItem(user, product, quantity);
            basketItemRepository.save(item);
            return ResponseEntity.ok("Product added to basket");
        }



    }




    @GetMapping("/{userId}")
    public ResponseEntity<List<BasketItemDto>> getUserBasket(@PathVariable Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<BasketItem> basketItems = basketItemRepository.findByUser(userOpt.get());

        List<BasketItemDto> dtoList = basketItems.stream()
                .map(item -> new BasketItemDto(
                        item.getId(),
                        item.getProduct().getName(),
                        item.getProduct().getPrice(),
                        item.getQuantity()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }


    @GetMapping("/{userId}/total")
    public ResponseEntity<Double> getTotalPrice(@PathVariable Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<BasketItem> items = basketItemRepository.findByUser(userOpt.get());
        double total = items.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        return ResponseEntity.ok(total);
    }


    @DeleteMapping("/{userId}/{itemId}")
    public ResponseEntity<String> deleteItem(
            @PathVariable Long userId,
            @PathVariable Long itemId) {

        Optional<BasketItem> itemOpt = basketItemRepository.findById(itemId);
        if (itemOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        BasketItem item = itemOpt.get();

        if (!item.getUser().getId().equals(userId)) {
            return ResponseEntity.status(403).body("Access denied: item does not belong to user");
        }

        basketItemRepository.delete(item);
        return ResponseEntity.ok("Item removed from basket");
    }


    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<String> clearBasket(@PathVariable Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<BasketItem> items = basketItemRepository.findByUser(userOpt.get());
        basketItemRepository.deleteAll(items);

        return ResponseEntity.ok("Basket cleared");
    }
}
