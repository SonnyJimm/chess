package com.cs489.chessGame.models;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @NotEmpty(message = "Username cant be empty")
    @Size(min = 4, max = 15, message = "Username too short")
    @Column(unique = true)
    private String username;
    @NotEmpty
    private String password;

    private Integer score;

    public User(String username, String password, Integer score) {
        this.username = username;
        this.password = password;
        this.score = score;
        games = new ArrayList<>();
    }

    @ManyToAny
    @JoinTable(name = "played_games", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "game_id"))
    private List<Game> games;
}
