package com.cs489.chessGame.models.Pieces;
import com.cs489.chessGame.models.Board;
import com.cs489.chessGame.models.Color;
import com.cs489.chessGame.models.Coordinate;

import java.util.List;

public class Bishop implements Piece{
    private static Bishop instance = new Bishop();

    private Bishop() {
    }

    public static Bishop getInstance() {
        return instance;
    }

    @Override
    public boolean isValidMove(Coordinate from, Coordinate to, Board board, Color color) {
        return isDiagonalValid(from,to,board,color);
    }

    @Override
    public List<Coordinate> validMovesFromCoordinate(Coordinate from, Board board, Color color) {
        return validMovesDiagonal(from);
    }
}
