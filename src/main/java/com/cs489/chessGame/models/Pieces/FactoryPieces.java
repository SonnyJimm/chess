package com.cs489.chessGame.models.Pieces;

//import org.example.Domain.Type;

import com.cs489.chessGame.models.Type;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FactoryPieces {
    private Map<Type, Piece> pieces;
    private static FactoryPieces instance = new FactoryPieces();
    public FactoryPieces(){
        Map<Type,Piece> piece = new HashMap<>();
        piece.put(Type.PAWN, Pawn.getInstance());
        piece.put(Type.ROOK, Rook.getInstance());
        piece.put(Type.KNIGHT, Knight.getInstance());
        piece.put(Type.BISHOP, Bishop.getInstance());
        piece.put(Type.KING, King.getInstance());
        piece.put(Type.QUEEN, Queen.getInstance());
        pieces = Collections.unmodifiableMap(piece);
    }
    public static Map<Type,Piece> getPieces (){
        return instance.pieces;
    }

}
