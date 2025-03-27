package com.inspire12.likelionwebsocket.controller;

import com.inspire12.likelionwebsocket.model.ChatMessage;
import com.inspire12.likelionwebsocket.service.MessageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatPersonalController {

    private final MessageService messageService;

    public ChatPersonalController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/call/user")
    public ChatMessage callUser(
            @RequestParam String username,
            @RequestBody ChatMessage chatMessage
    ) {
        System.out.println("username => " + username);
        return messageService.callUser(username, chatMessage);
    }
}
