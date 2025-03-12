package com.chatapp.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nickname;
    private String content;
    private LocalDateTime timestamp;
    
    public Message() {
        this.timestamp = LocalDateTime.now();
    }
    
    public Message(String nickname, String content) {
        this.nickname = nickname;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }
