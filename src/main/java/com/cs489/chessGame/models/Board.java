package com.cs489.chessGame.models;

import com.cs489.chessGame.models.Pieces.FactoryPieces;
import com.cs489.chessGame.models.Pieces.King;
import com.cs489.chessGame.models.Pieces.Pawn;
import com.cs489.chessGame.models.Pieces.Piece;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Entity
@Data
@AllArgsConstructor
@Table(name = "game_board")
public class Board {
    @Id
    @GeneratedValue
    private Integer id;
    @Transient
    private Field[][] gameBoard;
    @Transient
    private Map<Type, Piece> pieceMap = FactoryPieces.getPieces();
    @OneToMany
    private List<Record> history;
    public Board(Field[][] gameBoard) {
        this.gameBoard = gameBoard;
        history = new LinkedList<>();
    }
    public Board(){
        gameBoard = new Field[8][8];
        for (int i = 0; i < gameBoard[1].length; i++) {
            gameBoard[1][i] = new Field(Color.BLACK,Type.PAWN);
        }
        for (int i = 0; i < gameBoard[1].length; i++) {
            gameBoard[6][i] = new Field(Color.WHITE,Type.PAWN);
        }
        gameBoard[0][0] = new Field(Color.BLACK,Type.ROOK);
        gameBoard[0][7] = new Field(Color.BLACK,Type.ROOK);
        gameBoard[7][0] = new Field(Color.WHITE,Type.ROOK);
        gameBoard[7][7] = new Field(Color.WHITE,Type.ROOK);

        gameBoard[0][1] = new Field(Color.BLACK,Type.KNIGHT );
        gameBoard[0][6] = new Field(Color.BLACK,Type.KNIGHT);
        gameBoard[7][1] = new Field(Color.WHITE,Type.KNIGHT);
        gameBoard[7][6] = new Field(Color.WHITE,Type.KNIGHT);

        gameBoard[0][2] = new Field(Color.BLACK,Type.BISHOP);
        gameBoard[0][5] = new Field(Color.BLACK,Type.BISHOP);
        gameBoard[7][2] = new Field(Color.WHITE,Type.BISHOP);
        gameBoard[7][5] = new Field(Color.WHITE,Type.BISHOP);

        gameBoard[0][3] = new Field(Color.BLACK,Type.KING);
        gameBoard[0][4] = new Field(Color.BLACK,Type.QUEEN);
        gameBoard[7][3] = new Field(Color.WHITE,Type.KING);
        gameBoard[7][4] = new Field(Color.WHITE,Type.QUEEN);
        history = new LinkedList<>();
    }
    public boolean isValidMove(Coordinate from, Coordinate to, Color color){
        if(isOutBound(from) || isOutBound(to)){
            return false;
        }
        var field = gameBoard[from.getY()][from.getX()];
        if (field == null || color != field.getColor()){
            return false;
        }
        if(!pieceMap.containsKey(field.getType())){
            return false;
        }
        var piece = pieceMap.get(field.getType());
        return piece.isValidMove(from,to,this,color);
    }
    public boolean move(Coordinate from, Coordinate to, Color color){
        if(!isValidMove(from,to,color)){
            return false;
        }
        var capturedField = gameBoard[to.getY()][to.getX()];
        var movingField = gameBoard[from.getY()][from.getX()];

        gameBoard[from.getY()][from.getX()]= null;
        gameBoard[to.getY()][to.getX()] = movingField;

        if(isChecked(findKing(color),color)){
            gameBoard[to.getY()][to.getX()] = capturedField;
            gameBoard[from.getY()][from.getX()] = movingField;
            return false;
        }
//        System.out.println(gameBoard[from.getY()][to.getX()]);
//        System.out.println(movingField.getType());
//        System.out.println(Pawn.getInstance().isElPassant(from,to,this,color));
        gameBoard[to.getY()][to.getX()] = null;
        if(Type.PAWN == movingField.getType() && Pawn.getInstance().isElPassant(from,to,this,color)){
            capturedField=gameBoard[from.getY()][to.getX()];
            gameBoard[from.getY()][to.getX()] = null;
//            System.out.println("hi");
        }
        gameBoard[to.getY()][to.getX()] = movingField;
        var promotion = movingField.getType() == Type.PAWN && (to.getY() == 0 || to.getY() ==7);
        var moves = new ArrayList<Move>();
        moves.add(new Move(from,to));
        movingField.incrementMoveCounter();

        if(Type.KING == movingField.getType() && King.getInstance().isCastling(from,to,this,color)){
            int x = from.getX() < to.getX() ? 7 : 0;
            var rook = gameBoard[to.getY()][x];
            int sign = (int) Math.signum(to.getX()-from.getX());
            var midPoint = new Coordinate(from.getX()+sign, from.getY());
            rook.incrementMoveCounter();
            gameBoard[to.getY()][x] = null;
            gameBoard[midPoint.getY()][midPoint.getX()] = rook;
            moves.add(new Move(new Coordinate(x,to.getY()),midPoint));
        }
        promotePawn(color,null);
        history.add(new Record(moves,capturedField,promotion,color));
        return true;
    }
    public boolean isPromotable(Color color){
        if (color == Color.WHITE){
            for(int i=0; i<8; i++){
                if(gameBoard[0][i].getType() == Type.PAWN &&  gameBoard[0][i].getColor() == color){
                    return true;
                }
            }
        }else{
            for(int i=0; i<8; i++){
                if(gameBoard[7][i].getType() == Type.PAWN &&  gameBoard[7][i].getColor() == color ){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean promotePawn(Color color,Type type){
        if(type == null){
            type = Type.QUEEN;
        }
        if (color == Color.WHITE){
            for(int i=0; i<8; i++){
                if(gameBoard[0][i].getType() == Type.PAWN &&  gameBoard[0][i].getColor() == color){
                    gameBoard[0][i] = new Field(color,type);
                    return true;
                }
            }
        }else{
            for(int i=0; i<8; i++){
                if(gameBoard[7][i].getType() == Type.PAWN &&  gameBoard[7][i].getColor() == color ){
                    gameBoard[7][i] = new Field(color,type);
                    return true;
                }
            }
        }
        return false;
    }
    public Coordinate findKing(Color color){
        Coordinate king = null;
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                var field = gameBoard[i][j];
                if(field == null){
                    continue;
                }
                if (field.getColor() == color && field.getType() == Type.KING) {
                    king = new Coordinate(i,j);
                    break;
                }

            }
        }
        return king;
    }
    public boolean isChecked(Coordinate kingPosition, Color color){
        if(kingPosition == null){
            return false;
        }
        List<Coordinate> startingCoordinate = new ArrayList<>();
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                if(gameBoard[i][j] == null){
                    continue;
                }
                if (gameBoard[i][j].getColor() != color) {
                    Coordinate move = new Coordinate(i,j);
                    startingCoordinate.add(move);
                }

            }
        }
        return 0 < startingCoordinate.stream().filter(move->{
            return pieceMap.get(gameBoard[move.getY()][move.getX()]).isValidMove(move,kingPosition,this,color);
        }).count();
    }
    public boolean isOutBound(Coordinate coordinate) {
        if (coordinate.getY() < 0 || coordinate.getY() >= gameBoard.length || coordinate.getX() < 0 || coordinate.getX() >= gameBoard[0].length) {
            return true;
        }
        return false;
    }
    public List<Record> getHistory() {
        return history;
    }
    public Field[][] getGameBoard() {
        return gameBoard;
    }
}
