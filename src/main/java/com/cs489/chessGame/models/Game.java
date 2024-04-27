package com.cs489.chessGame.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name="white_team_user_id", referencedColumnName = "id")
    private User whiteTeam;
    @ManyToOne
    @JoinColumn(name="black_team_user_id", referencedColumnName = "id")
    private User blackTeam;
    private Result result;
    @Transient
    private WebSocketSession whiteSession;
    @Transient
    private WebSocketSession blackSession;
    @Transient
    private Color turn;
    @OneToOne
    private Board board;
    public Game(WebSocketSession whiteSession, WebSocketSession blackSession) {
        this.whiteSession = whiteSession;
        this.blackSession = blackSession;
        try {
            whiteSession.sendMessage(new TextMessage("Game has started"));
            blackSession.sendMessage(new TextMessage("Game has started"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        turn = Color.WHITE;
        this.board= new Board();
    }

    public void gameFinished(Result result){
        this.result = result;
        try{
            blackSession.close();
            whiteSession.close();
        }catch (IOException e){
            System.out.println(e);
        }
    }
}
