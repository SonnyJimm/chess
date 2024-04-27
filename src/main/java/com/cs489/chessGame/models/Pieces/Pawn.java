package com.cs489.chessGame.models.Pieces;


import com.cs489.chessGame.models.Board;
import com.cs489.chessGame.models.Color;
import com.cs489.chessGame.models.Coordinate;
import com.cs489.chessGame.models.Type;

import java.util.ArrayList;
import java.util.List;

/*
* @TODO el passant
* */

public class Pawn implements Piece{
    private static Pawn pawn = new Pawn();
    private Pawn(){}

    public static Pawn getInstance(){
        return pawn;
    }
    @Override
    public boolean isValidMove(Coordinate from, Coordinate to, Board board, Color color) {
        if(isSamePosition(from,to) || isOutBound(from) || isOutBound(to)){
            return false;
        }
        int sign = (int)Math.signum(to.getY()-from.getY());
        // check for valid move direction
//        System.out.println("here "+sign);
        if (sign != color.getDirection()){
            return false;
        }
        int xDirection = Math.abs(from.getX() - to.getX());
        int yDirection = Math.abs(from.getY() - to.getY());
        var gameBoard = board.getGameBoard();
        if (gameBoard[from.getY()][from.getX()] == null && gameBoard[from.getY()][from.getX()].getColor() != color){
            return false;
        }
        if (yDirection == 2 && xDirection==0){
            if (gameBoard[to.getY()][to.getX()] == null && gameBoard[from.getY()+sign][from.getX()] == null && gameBoard[from.getY()][from.getX()].getMoveCount() == 0){
                return true;
            }
        }
        if (yDirection == 1 && xDirection==0){
            if (gameBoard[to.getY()][to.getX()] == null ){
                return true;
            }
        }

        if (yDirection == 1 && xDirection==1){
            if (gameBoard[to.getY()][to.getX()] != null && gameBoard[to.getY()][to.getX()].getColor() != color){
                return true;
            }
            //el passant check todo
            // please for the love of god refactor this future me
            if (gameBoard[to.getY()][to.getX()] == null && gameBoard[from.getY()][to.getX()] != null
                    && gameBoard[from.getY()][to.getX()].getColor() != color && gameBoard[from.getY()][to.getX()].getMoveCount() == 1
                    && gameBoard[from.getY()][to.getX()].getType() == Type.PAWN ){
                    var history = board.getHistory();
                    var yDist = Math.abs(from.getY() - (color == Color.WHITE ? 1 : 6));
                    if (yDist != 2){
                        return false;
                    }
                    if (history.size() < 2){
                        System.out.println("this should have not happened but here we are");
                        return false;
                    }
                    var prevOponent = history.get(history.size()-1);
                    var prevOwn = history.get(history.size()-2);
                    if (prevOwn.getColor() != color || prevOponent.getColor() == color){
                        System.out.println("Order of history is not working");
                        return false;
                    }
                    var prevMoveOwn = prevOwn.getMoves().get(0);
                    var prevMoveOponent = prevOponent.getMoves().get(0);
                    if(prevMoveOwn.getTo().equals(from) || prevMoveOponent.getTo().equals(new Coordinate(to.getX(), from.getY()))){
                        return true;
                    }
            }
        }


        return false;
    }
    public boolean isElPassant(Coordinate from, Coordinate to, Board board, Color color){
        var gameBoard = board.getGameBoard();
        if (gameBoard[to.getY()][to.getX()] == null && gameBoard[from.getY()][to.getX()] != null
                && gameBoard[from.getY()][to.getX()].getColor() != color && gameBoard[from.getY()][to.getX()].getMoveCount() == 1
                && gameBoard[from.getY()][to.getX()].getType() == Type.PAWN ){
            return true;
        }
        return false;
    }

    @Override
    public List<Coordinate> validMovesFromCoordinate(Coordinate from, Board board, Color color) {
        var moves= new ArrayList<Coordinate>();
        moves.add(new Coordinate(from.getX(), from.getX()+(2*color.getDirection())));
        for(int x = from.getX()-1; x <= from.getX()+1; x++){
            moves.add(new Coordinate(x, from.getX()+(color.getDirection())));
        }
        return moves;
    }
}
