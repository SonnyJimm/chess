package com.cs489.chessGame.models;

public enum Color {
    WHITE(-1),
    BLACK(1);
    private final Integer direction;
    Color(int direction){
        this.direction = direction;
    }
    public Integer getDirection() {
        return this.direction;
    }
}