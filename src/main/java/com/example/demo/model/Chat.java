package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    public Chat(String text) {
        this.text = text;
    }
    public Chat() {}

   public long getId(){return id;}
   public String getText(){return text;}

}
