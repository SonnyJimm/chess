package com.cs489.chessGame.models.Pieces;

import com.cs489.chessGame.models.Board;
import com.cs489.chessGame.models.Color;
import com.cs489.chessGame.models.Coordinate;
import com.cs489.chessGame.models.Type;

import java.util.ArrayList;
import java.util.List;

public class King implements Piece{
    private static King instance = new King();

    public static King getInstance() {
        return instance;
    }

    private King() {
    }

    @Override
    public boolean isValidMove(Coordinate from, Coordinate to, Board board, Color color) {
        if(isSamePosition(from,to) || isOutBound(from) || isOutBound(to)){
            return false;
        }
        var gameBoard = board.getGameBoard();
        var tile = gameBoard[to.getY()][to.getX()];
        if (tile != null && tile.getColor() == color){
            return false;
        }

        int xDirection = Math.abs(from.getX() - to.getX());
        int yDirection = Math.abs(from.getY() - to.getY());

        // check for castling
        if (xDirection == 2 && yDirection ==0 ){
            int x = from.getX() < to.getX() ? 7 : 0;
            var rook = gameBoard[to.getY()][x];
            var king = gameBoard[from.getY()][from.getX()];
            System.out.println(rook);
            System.out.println(x+ " "+ to.getY());
            if(rook == null || king == null || rook.getType() != Type.ROOK || rook.getMoveCount() != 0 || king.getMoveCount()!=0 || rook.getColor() != color){
                return false;
            }
            int sign = (int) Math.signum(to.getX()-from.getX());
            var midPoint = new Coordinate(from.getX()+sign, from.getY());
            if(!isStraightValid(new Coordinate(x, from.getY()),midPoint,board,color)){
                return false;
            }
            if(board.isChecked(from,color) || board.isChecked(midPoint,color) || board.isChecked(to,color)){
                return false;
            }
            return true;

        }
        if (xDirection <=1 && yDirection <=1){
            return true;
        }
        return false;
    }

    public boolean isCastling(Coordinate from, Coordinate to, Board board, Color color){
        int xDirection = Math.abs(from.getX() - to.getX());
        int yDirection = Math.abs(from.getY() - to.getY());
        if (xDirection == 2 && yDirection ==0 ){
            return true;
        }
        return false;
    }
    @Override
    public List<Coordinate> validMovesFromCoordinate(Coordinate from, Board board, Color color) {
        return new ArrayList<>();
    }
}
