package com.cs489.chessGame.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class Field {
    @Enumerated(EnumType.STRING)
    @Column(name = "capture_field_color")
    private Color color;
    private Type type;
    private int moveCounter;
    public Field(Color color, Type type) {
        this.color = color;
        this.type = type;
        moveCounter = 0;
    }

    public Color getColor() {
        return color;
    }
    public Type getType() {
        return type;
    }
    public void incrementMoveCounter(){
        moveCounter++;
    }
    public int getMoveCount() {
        return moveCounter;
    }

    @Override
    public String toString() {
        var res = "[";
        switch (type){
            case KING -> {res+="K";break;}
            case BISHOP ->{res+="B";break;}
            case KNIGHT -> {res+="N";break;}
            case QUEEN ->{res+="Q";break;}
            case ROOK ->{res+="R";break;}
            case PAWN ->{res+="p";break;}
        }
        return res + "]";
    }
}
