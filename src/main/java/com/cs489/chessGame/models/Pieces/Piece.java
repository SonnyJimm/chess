package com.cs489.chessGame.models.Pieces;

import com.cs489.chessGame.models.Board;
import com.cs489.chessGame.models.Color;
import com.cs489.chessGame.models.Coordinate;

import java.util.ArrayList;
import java.util.List;


public interface Piece {
    public boolean isValidMove(Coordinate from, Coordinate to, Board board, Color color);
    public List<Coordinate> validMovesFromCoordinate(Coordinate from, Board board, Color color);
    default  boolean isOutBound(Coordinate coordinate){
        if (coordinate.getX() < 0 || coordinate.getX() > 7 || coordinate.getY() < 0 || coordinate.getY() > 7) {
            return true;
        }
        return false;
    }
    default boolean isOutBound(int i, int j) {
        if (i < 0 || i > 7 || j < 0 || j > 7) {
            return true;
        }
        return false;
    }
    default List<Coordinate> validMovesStraight(Coordinate from){
        var list = new ArrayList<Coordinate>();
        var moves = new int[][]{
                {1,0},
                {-1,0},
                {0,1},
                {0,-1},
        };
        for (int i=0; i<moves.length; i++){
            int x = from.getX()+moves[i][0];
            int y = from.getY()+moves[i][1];
            while(!isOutBound(x,y)){
                list.add(new Coordinate(x,y));
                x+=moves[i][0];
                y+=moves[i][1];
            }
        }
        return list;
    }
    default List<Coordinate> validMovesDiagonal(Coordinate from){
        var list = new ArrayList<Coordinate>();
        var moves = new int[][]{
                {1,1},
                {-1,1},
                {-1,-1},
                {1,-1},
        };
        for (int i=0; i<moves.length; i++){
            int x = from.getX()+moves[i][0];
            int y = from.getY()+moves[i][1];
            while(!isOutBound(x,y)){
                list.add(new Coordinate(x,y));
                x+=moves[i][0];
                y+=moves[i][1];
            }
        }
        return list;
    }
    // checks if it is diagonally valid and between the destination is there anything obstructing
    default  boolean isDiagonalValid(Coordinate from, Coordinate to, Board board, Color color){
        if(isSamePosition(from,to) || isOutBound(from) || isOutBound(to)){
            return false;
        }
        int distanceX = Math.abs(from.getX() - to.getX());
        int distanceY = Math.abs(from.getY() - to.getY());
        if (distanceY == distanceX){

            int xStart= 0, xFinish= 0;
            int yStart= 0, yDirection= 0;

            var gameBoard = board.getGameBoard();
            if(gameBoard[to.getY()][to.getX()] != null &&  gameBoard[to.getY()][to.getX()].getColor() == color){
                return false;
            }
            if (from.getX() < to.getX()){
                xStart = from.getX();
                xFinish = to.getX();
                yStart = from.getY();
                yDirection = (int) Math.signum(to.getY() -from.getY());
            }else{
                xStart = to.getX();
                xFinish = from.getX();
                yStart = to.getY();
                yDirection = (int) Math.signum(from.getY() - to.getY());
            }
            xStart++;
            yStart+=yDirection;
            while (xStart < xFinish){
                if (gameBoard[yStart][xStart] != null){
                    return false;
                }
                xStart++;
                yStart+=yDirection;
            }
            return true;
        }
        return false;
    }
    default  boolean isSamePosition(Coordinate from, Coordinate to){
        if (from.getX() == to.getX() && from.getY() == to.getY()){
            return true;
        }
        return false;
    }
    default  boolean isStraightValid(Coordinate from, Coordinate to, Board board, Color color){
        int xDistance = Math.abs(from.getX() - to.getX());
        int yDistance = Math.abs(from.getY() - to.getY());

        if(isSamePosition(from,to) || isOutBound(from) || isOutBound(to)){
            return false;
        }
        if(xDistance == 0 ){
                int yStart = from.getY();
                int yFinish = to.getY();
                int x = from.getX();
                var gameBoard = board.getGameBoard();

                if(yStart > yFinish){
                    yStart = to.getY();
                    yFinish = from.getY();
                }

                if(gameBoard[to.getY()][to.getX()] != null && gameBoard[to.getY()][to.getX()].getColor() == color){
                    return false;
                }

                for(int y = yStart+1; y<yFinish; y++){
                    if (gameBoard[y][x] != null){
                        return false;
                    }
                }
                return true;
        }
        if(yDistance == 0){
            int xStart = from.getX();
            int xFinish = to.getX();
            int y = from.getY();
            var gameBoard = board.getGameBoard();

            if(xStart > xFinish){
                xStart = to.getX();
                xFinish = from.getX();
            }

            if(gameBoard[to.getY()][to.getX()] != null && gameBoard[to.getY()][to.getX()].getColor() == color){
                return false;
            }

            for(int x=xStart+1; x<xFinish; x++){
                if(gameBoard[y][x] != null){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

//    boolean isValidMove(Coordinate from, Coordinate to, Board board, Color color);
}
