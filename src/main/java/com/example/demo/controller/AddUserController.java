package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.MainService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/new_user")
public class AddUserController {

    private final MainService service;

    public AddUserController(MainService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> addUser(@RequestBody User user) {
        try {
            service.addUser(user);
            return ResponseEntity.ok(Map.of("message", "User is saved"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(400)
                    .body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping
    public String info() {
        return "Send POST request with JSON to save user";
    }
}
