package com.cs489.chessGame.controller;

import com.cs489.chessGame.DTO.message.MessageDTO;
import com.cs489.chessGame.DTO.message.ResponseMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

public class GameMessageController extends TextWebSocketHandler {
    @Autowired
    private SessionHolder sessionHolder;
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        System.out.println(session.getPrincipal().getName());
        session.sendMessage(message);
    }
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionHolder.add(session);
        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionHolder.remove(session);
        super.afterConnectionClosed(session, status);
    }

}
