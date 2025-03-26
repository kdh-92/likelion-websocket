package com.inspire12.likelionwebsocket.service;

import com.inspire12.likelionwebsocket.model.ChatMessage;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    public ChatMessage createWelcomeMessage(ChatMessage chatMessage) {
        ChatMessage welcomeMessage = ChatMessage.builder()
                .sender("System")
                .content(
                        String.format("""
                        %s 님이 들어왔습니다.
                        """, chatMessage.getSender()))
                .type(ChatMessage.MessageType.JOIN)
                .build();

        return welcomeMessage;
    }

    public ChatMessage createOutMessage(ChatMessage chatMessage) {
        ChatMessage outMessage = ChatMessage.builder()
                .sender("System")
                .content(
                        String.format("""
                        %s 님이 나갔습니다...
                        """, chatMessage.getSender()))
                .type(ChatMessage.MessageType.LEAVE)
                .build();

        return outMessage;
    }
}
