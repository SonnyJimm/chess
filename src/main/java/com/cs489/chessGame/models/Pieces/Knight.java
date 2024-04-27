package com.cs489.chessGame.models.Pieces;

import com.cs489.chessGame.models.Board;
import com.cs489.chessGame.models.Color;
import com.cs489.chessGame.models.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class Knight implements Piece{
    private static Knight instance = new Knight();
    public static Knight getInstance(){return instance;}
    private Knight(){}
    @Override
    public boolean isValidMove(Coordinate from, Coordinate to, Board board, Color color) {
        if(isSamePosition(from,to) || isOutBound(from) || isOutBound(to)){
            return false;
        }
        var tile = board.getGameBoard()[to.getY()][to.getX()];
        if (tile != null && tile.getColor() == color){
            return false;
        }
        if (Math.abs(from.getX() - to.getX()) == 2 && Math.abs(from.getY()-to.getY()) == 1){
            return true;
        }
        if (Math.abs(from.getX() - to.getX()) == 1 && Math.abs(from.getY()-to.getY()) == 2){
            return true;
        }
        return false;
    }

    @Override
    public List<Coordinate> validMovesFromCoordinate(Coordinate from, Board board, Color color) {
        var moves = new ArrayList<Coordinate>();
        var dirs = new int[][]{
                {1,2},
                {1,-2},
                {-1,2},
                {-1,-2},
                {2,1},
                {2,-1},
                {-2,1},
                {-2,-1},
        };
        for (int[] dir : dirs){
            moves.add(new Coordinate(from.getX()+dir[0], from.getY() + dir[1]));
        }

        return moves;
    }
}
