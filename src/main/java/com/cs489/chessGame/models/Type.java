package com.cs489.chessGame.models;

public enum Type {
    PAWN(1),
    ROOK(4),
    KING(6),
    QUEEN(5),
    BISHOP(3),
    KNIGHT(2);
    private final Integer power;
    Type(int power){
        this.power = power;
    }
    public Integer getPower() {
        return this.power;
    }
}
