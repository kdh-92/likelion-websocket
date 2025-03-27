package com.inspire12.likelionwebsocket.handshake;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

@Component
public class CustomHandshakeHandler extends DefaultHandshakeHandler {

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {

        String username = getUsernameFromRequest(request);

        return new Principal() {
            @Override
            public String getName() {
                return username;
            }
        };
    }

    private String getUsernameFromRequest(ServerHttpRequest request) {
        String query = request.getURI().getQuery();
        if (query != null && query.contains("username=")) {
            return query.split("=")[1];
        }
        return null;
    }
}
