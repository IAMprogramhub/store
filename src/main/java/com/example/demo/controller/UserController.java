package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<UserDto> getAllUsers(
            //@RequestParam (required = false, defaultValue = "name")
            String sort) {
        List<User> users = userRepository.findAll(
                //Sort.by(sort)
        );
        return users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
//    @PutMapping("/{id}")



    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(value -> ResponseEntity.ok(convertToDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @PostMapping
//    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
//
//        User user = new User(userDto.getUsername(), userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//
//
//
//        User savedUser = userRepository.save(user);
//
//
//        return ResponseEntity.ok(convertToDto(savedUser));
//    }


    private UserDto convertToDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getEmail());
    }
}

