package com.cs489.chessGame.configs;

import com.cs489.chessGame.controller.GameMessageController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
//@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketConfigurer {
    @Bean
    public GameMessageController gameMessageController(){
        return new GameMessageController();
    };
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(gameMessageController(), "/handler");
    }


//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
////        registry.addEndpoint("/ws");
//    }
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry registry) {
////        registry.setApplicationDestinationPrefixes("/topic");
////        registry.enableSimpleBroker("/ws");
//    }
}
