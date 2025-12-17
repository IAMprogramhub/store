package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public MainService(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public void addUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));

        user.setRole("ROLE_USER");
        repository.save(user);

    }
}
