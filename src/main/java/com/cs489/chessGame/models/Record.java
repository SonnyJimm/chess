package com.cs489.chessGame.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Record {
    @Id
    @GeneratedValue
    private Integer id;
    @OneToMany
    List<Move> moves;
    @Embedded
    Field CapturedField;
    boolean promotion;
    @Enumerated(EnumType.STRING)
    Color color;


    public Record(List<Move> moves, Field capturedField, boolean promotion, Color color) {
        this.moves = moves;
        CapturedField = capturedField;
        this.promotion = promotion;
        this.color = color;
    }
    public List<Move> getMoves() {
        return moves;
    }

    public Field getCapturedField() {
        return CapturedField;
    }

    public boolean isPromotion() {
        return promotion;
    }

    public Color getColor() {
        return color;
    }
}
