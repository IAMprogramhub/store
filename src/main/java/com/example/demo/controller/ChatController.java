package com.example.demo.controller;

import com.example.demo.model.Chat;
import com.example.demo.repository.MessageRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ChatController {
    private final MessageRepository repo;

    public ChatController(MessageRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    public String chat(Model model) {
        model.addAttribute("messages", repo.findAll());
        return "chat";
    }

    @PostMapping("/send")
    public String send(@RequestParam String msg) {
        if (!msg.trim().isEmpty()) {
            repo.save(new Chat ("User: " + msg));
        }
        return "redirect:/";
    }
}
