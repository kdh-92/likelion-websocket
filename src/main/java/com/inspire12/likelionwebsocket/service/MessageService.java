package com.inspire12.likelionwebsocket.service;

import com.inspire12.likelionwebsocket.model.ChatMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final SimpMessagingTemplate messagingTemplate;

    public MessageService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

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

    public ChatMessage callUser(String username, ChatMessage chatMessage) {

        ChatMessage chatMessageToUser = ChatMessage.builder()
                .sender(chatMessage.getSender())
                .type(ChatMessage.MessageType.CHAT)
                .content("귓속말 : " + chatMessage.getContent())
                .build();

        messagingTemplate.convertAndSendToUser(username, "/queue/private", chatMessageToUser);

        return chatMessageToUser;
    }
}
