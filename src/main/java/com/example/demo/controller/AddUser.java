package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.MainService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/new_user")
public class AddUser {


    private final MainService service;

    public AddUser(MainService service) {
        this.service = service;
    }

    @PostMapping
    public String addUser(@RequestBody User user) {
        service.addUser(user);
        return "user is saved";
    }
    @GetMapping
    public String info() {
        return "Send POST request with JSON to save user";
    }
}