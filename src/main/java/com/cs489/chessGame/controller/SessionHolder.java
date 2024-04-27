package com.cs489.chessGame.controller;


import com.cs489.chessGame.models.Game;
import com.cs489.chessGame.models.Result;
import com.cs489.chessGame.repo.GameRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;

@Controller
public class SessionHolder {
    private GameRepository gameRepository;
    private List<WebSocketSession> onlineUsers = Collections.synchronizedList(new ArrayList<>());
    private List<WebSocketSession> queue = Collections.synchronizedList(new ArrayList<>());
    private List<Game> gameBoard = Collections.synchronizedList(new ArrayList<>());
    public void add(WebSocketSession session){
        onlineUsers.add(session);
        queue.add(session);
        if(queue.size() >= 2){
            gameBoard.add(new Game(queue.get(0),queue.get(1)));
            queue.remove(0);
            queue.remove(0);

        }
    }
    public void remove(WebSocketSession session){
        onlineUsers.remove(session);
        queue.remove(session);

        gameBoard.stream().forEach(game -> {
            if(game.getBlackSession().equals(session) ){
                try {
                    game.getWhiteSession().sendMessage(new TextMessage("Game has ended"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                game.gameFinished(Result.WHITE_WINNER);
                gameRepository.save(game);
            }
            if(game.getWhiteSession().equals(session)){
                game.gameFinished(Result.BLACK_WINNER);
                try {
                    game.getBlackSession().sendMessage(new TextMessage("Game has ended"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                gameRepository.save(game);
            }
        });

    }
}
