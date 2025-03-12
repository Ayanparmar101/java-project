package com.chatapp.controller;

import com.chatapp.model.Message;
import com.chatapp.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ChatController {
    
    @Autowired
    private MessageRepository messageRepository;
    
    @GetMapping("/")
    public String index(Model model) {
        List<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);
        return "index";
    }
    
    @PostMapping("/send")
    public String sendMessage(@RequestParam String nickname, @RequestParam String content) {
        Message message = new Message(nickname, content);
        messageRepository.save(message);
        return "redirect:/";
    }
 // For WebSocket implementation
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Message sendViaWebSocket(Message message) {
        messageRepository.save(message);
        return message;
    }
}
