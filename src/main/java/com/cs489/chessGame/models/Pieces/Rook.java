package com.cs489.chessGame.models.Pieces;


import com.cs489.chessGame.models.Board;
import com.cs489.chessGame.models.Color;
import com.cs489.chessGame.models.Coordinate;

import java.util.List;

public class Rook implements Piece{
    private static Rook instance = new Rook();

    private Rook() {
    }

    public static Rook getInstance() {
        return instance;
    }
    @Override
    public boolean isValidMove(Coordinate from, Coordinate to, Board board, Color color) {
        return isStraightValid(from,to,board,color);
    }

    @Override
    public List<Coordinate> validMovesFromCoordinate(Coordinate from, Board board, Color color) {
        return validMovesStraight(from);
    }
}
