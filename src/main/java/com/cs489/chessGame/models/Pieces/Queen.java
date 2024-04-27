package com.cs489.chessGame.models.Pieces;


import com.cs489.chessGame.models.Board;
import com.cs489.chessGame.models.Color;
import com.cs489.chessGame.models.Coordinate;

import java.util.List;

public class Queen implements Piece{
    private static Queen instance = new Queen();
    public static Queen getInstance (){
        return instance;
    }
    private Queen(){}
    @Override
    public boolean isValidMove(Coordinate from, Coordinate to, Board board, Color color) {
        return isStraightValid(from,to,board,color) || isDiagonalValid(from,to,board,color);
    }

    @Override
    public List<Coordinate> validMovesFromCoordinate(Coordinate from, Board board, Color color) {
        var moves = validMovesDiagonal(from);
        moves.addAll(validMovesStraight(from));
        return moves;
    }
}
