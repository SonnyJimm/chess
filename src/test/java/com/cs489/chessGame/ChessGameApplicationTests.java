package com.cs489.chessGame;

import com.cs489.chessGame.models.*;
import com.cs489.chessGame.models.Pieces.Bishop;
import com.cs489.chessGame.models.Pieces.King;
import com.cs489.chessGame.models.Pieces.Knight;
import com.cs489.chessGame.models.Pieces.Rook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChessGameApplicationTests {
	public static void bishopTest(){
		var fields = new Field[8][8];
		var board = new Board(fields);
		fields[3][4] = new Field(Color.BLACK, Type.BISHOP);
		var bishop =  Bishop.getInstance();
		System.out.println("Bishop Should be valid : "+bishop.isDiagonalValid(new Coordinate(4,3), new Coordinate(1,0),board,Color.BLACK));
		System.out.println("Bishop Should be valid : "+bishop.isDiagonalValid(new Coordinate(4,3), new Coordinate(7,6),board,Color.BLACK));
		System.out.println("Bishop Should be valid : "+bishop.isDiagonalValid(new Coordinate(4,3), new Coordinate(7,0),board,Color.BLACK));
		System.out.println("Bishop Should be valid : "+bishop.isDiagonalValid(new Coordinate(4,3), new Coordinate(0,7),board,Color.BLACK));

		System.out.println("Bishop Should be invalid : "+bishop.isDiagonalValid(new Coordinate(4,3), new Coordinate(2,0),board,Color.BLACK));
		System.out.println("Bishop Should be invalid : "+bishop.isDiagonalValid(new Coordinate(4,3), new Coordinate(2,6),board,Color.BLACK));
		System.out.println("Bishop Should be invalid : "+bishop.isDiagonalValid(new Coordinate(4,3), new Coordinate(5,3),board,Color.BLACK));
		fields[1][2] = new Field(Color.WHITE, Type.BISHOP);
		System.out.println("Bishop Should be invalid : "+bishop.isDiagonalValid(new Coordinate(4,3), new Coordinate(1,0),board,Color.BLACK));
		fields[1][2] = null;
		fields[0][1] = new Field(Color.WHITE, Type.BISHOP);
		System.out.println("Bishop Should be valid : "+bishop.isDiagonalValid(new Coordinate(4,3), new Coordinate(1,0),board,Color.BLACK));
		fields[0][1] = new Field(Color.BLACK, Type.BISHOP);
		System.out.println("Bishop Should be invalid : "+bishop.isDiagonalValid(new Coordinate(4,3), new Coordinate(1,0),board,Color.BLACK));
		fields[4][3] = new Field(Color.WHITE, Type.BISHOP);
		System.out.println("Bishop Should be valid : "+bishop.isDiagonalValid(new Coordinate(4,3), new Coordinate(3,4),board,Color.BLACK));
	}
	public static void rookTest(){
		var fields = new Field[8][8];
		var board = new Board(fields);
		var rook =  Rook.getInstance();
		fields[4][4]= new Field(Color.BLACK,Type.ROOK);
		var origin = new Coordinate(4,4);
		System.out.println("Rook should b valid " + rook.isValidMove(origin,new Coordinate(0,4),board,Color.BLACK));
		System.out.println("Rook should b valid " + rook.isValidMove(origin,new Coordinate(7,4),board,Color.BLACK));
		System.out.println("Rook should b valid " + rook.isValidMove(origin,new Coordinate(4,7),board,Color.BLACK));
		System.out.println("Rook should b valid " + rook.isValidMove(origin,new Coordinate(4,0),board,Color.BLACK));


		System.out.println("Rook should b invalid " + rook.isValidMove(origin,new Coordinate(1,3),board,Color.BLACK));
		System.out.println("Rook should b invalid " + rook.isValidMove(origin,new Coordinate(4,4),board,Color.BLACK));
		System.out.println("Rook should b invalid " + rook.isValidMove(origin,new Coordinate(5,7),board,Color.BLACK));
		System.out.println("Rook should b invalid " + rook.isValidMove(origin,new Coordinate(2,2),board,Color.BLACK));

		fields[4][0]= new Field(Color.WHITE,Type.ROOK);
		System.out.println("Rook should b valid " + rook.isValidMove(origin,new Coordinate(0,4),board,Color.BLACK));
		fields[4][6]= new Field(Color.WHITE,Type.ROOK);
		System.out.println("Rook should b invalid " + rook.isValidMove(origin,new Coordinate(7,4),board,Color.BLACK));
		fields[7][4]= new Field(Color.BLACK,Type.ROOK);
		System.out.println("Rook should b invalid " + rook.isValidMove(origin,new Coordinate(4,7),board,Color.BLACK));
		fields[1][4]= new Field(Color.BLACK,Type.ROOK);
		System.out.println("Rook should b invalid " + rook.isValidMove(origin,new Coordinate(4,0),board,Color.BLACK));
	}
	public static void knightTest(){
		var fields = new Field[8][8];
		var board = new Board(fields);
		var knight = Knight.getInstance();
		fields[4][4]= new Field(Color.BLACK, Type.KNIGHT);
		var origin = new Coordinate(4,4);
		System.out.println("Knight should be valid: " + knight.isValidMove(origin,new Coordinate(2,3),board,Color.BLACK));
		System.out.println("Knight should be valid: " + knight.isValidMove(origin,new Coordinate(6,5),board,Color.BLACK));
		System.out.println("Knight should be valid: " + knight.isValidMove(origin,new Coordinate(5,2),board,Color.BLACK));
		System.out.println("Knight should be valid: " + knight.isValidMove(origin,new Coordinate(3,6),board,Color.BLACK));


		System.out.println("Knight should be invalid: " + knight.isValidMove(origin,new Coordinate(1,3),board,Color.BLACK));
		System.out.println("Knight should be invalid: " + knight.isValidMove(origin,new Coordinate(4,4),board,Color.BLACK));
		System.out.println("Knight should be invalid: " + knight.isValidMove(origin,new Coordinate(5,7),board,Color.BLACK));
		System.out.println("Knight should be invalid: " + knight.isValidMove(origin,new Coordinate(2,2),board,Color.BLACK));

		fields[2][3]= new Field(Color.WHITE,Type.KNIGHT);
		System.out.println("Knight should be valid: " + knight.isValidMove(origin,new Coordinate(3,2),board,Color.BLACK));
		fields[4][6]= new Field(Color.WHITE,Type.KNIGHT);
		System.out.println("Knight should be invalid: " + knight.isValidMove(origin,new Coordinate(7,4),board,Color.BLACK));
		fields[2][5]= new Field(Color.BLACK,Type.KNIGHT);
		System.out.println("Knight should be invalid: " + knight.isValidMove(origin,new Coordinate(5,2),board,Color.BLACK));
		fields[1][4]= new Field(Color.BLACK,Type.KNIGHT);
		System.out.println("Knight should be invalid: " + knight.isValidMove(origin,new Coordinate(4,0),board,Color.BLACK));

		// Test moves that go out of bounds
		System.out.println("Knight should be invalid: " + knight.isValidMove(origin,new Coordinate(3, -1),board,Color.BLACK));
		System.out.println("Knight should be invalid: " + knight.isValidMove(origin,new Coordinate(-1, 6),board,Color.BLACK));
		System.out.println("Knight should be invalid: " + knight.isValidMove(origin,new Coordinate(8, 5),board,Color.BLACK));
		System.out.println("Knight should be invalid: " + knight.isValidMove(origin,new Coordinate(5, 8),board,Color.BLACK));
	}
	public static void kingTest(){
		var fields = new Field[8][8];
		var board = new Board(fields);
		var king = King.getInstance();
		fields[4][4]= new Field(Color.BLACK, Type.KING);
		var origin = new Coordinate(4,4);
		System.out.println("King should be valid: " + king.isValidMove(origin,new Coordinate(3,3),board,Color.BLACK));
		System.out.println("King should be valid: " + king.isValidMove(origin,new Coordinate(3,4),board,Color.BLACK));
		System.out.println("King should be valid: " + king.isValidMove(origin,new Coordinate(3,5),board,Color.BLACK));
		System.out.println("King should be valid: " + king.isValidMove(origin,new Coordinate(4,3),board,Color.BLACK));
		System.out.println("King should be valid: " + king.isValidMove(origin,new Coordinate(4,5),board,Color.BLACK));
		System.out.println("King should be valid: " + king.isValidMove(origin,new Coordinate(5,3),board,Color.BLACK));
		System.out.println("King should be valid: " + king.isValidMove(origin,new Coordinate(5,4),board,Color.BLACK));
		System.out.println("King should be valid: " + king.isValidMove(origin,new Coordinate(5,5),board,Color.BLACK));

		System.out.println("King should be invalid: " + king.isValidMove(origin,new Coordinate(2,3),board,Color.BLACK));
		System.out.println("King should be invalid: " + king.isValidMove(origin,new Coordinate(3,2),board,Color.BLACK));

		System.out.println("King should be invalid: " + king.isValidMove(origin,new Coordinate(2,4),board,Color.BLACK));
		System.out.println("King should be invalid: " + king.isValidMove(origin,new Coordinate(6,4),board,Color.BLACK));
		//should be valid
		fields[4][0]= new Field(Color.BLACK, Type.ROOK);
		fields[4][7]= new Field(Color.BLACK, Type.ROOK);
		System.out.println("King should be valid: " + board.move(origin,new Coordinate(2,4),Color.BLACK));
		System.out.println("King should be invalid: " + board.move(origin,new Coordinate(6,4),Color.BLACK));
		for (int i=0; i<8;i++){
			for (int j=0; j<8;j++){
				if (board.getGameBoard()[i][j] == null){
					System.out.print("[ ]");
				}else{
					System.out.print(board.getGameBoard()[i][j]);
				}
			}
			System.out.println();
		}

	}
	public static void pawnTest(){
		var fields = new Field[8][8];
		var board = new Board(fields);
		fields[1][4] = new Field(Color.BLACK,Type.PAWN);
		var origin = new Coordinate(4,1);
		System.out.println( "Should be valid "+board.isValidMove(origin,new Coordinate(4,2),Color.BLACK));
		System.out.println("Should be valid "+board.isValidMove(origin,new Coordinate(4,3),Color.BLACK));
		System.out.println("Should be invalid "+board.isValidMove(origin,new Coordinate(4,4),Color.BLACK));
		System.out.println("Should be invalid "+board.isValidMove(origin,new Coordinate(4,0),Color.BLACK));
		fields[6][3] = new Field(Color.WHITE,Type.PAWN);
		fields[0][0] = new Field(Color.WHITE,Type.ROOK);
		System.out.println("black move "+board.move(origin,new Coordinate(4,3),Color.BLACK));
		System.out.println("white move "+board.move(new Coordinate(3,6),new Coordinate(3,4),Color.WHITE));
		System.out.println("black move "+board.move(new Coordinate(4,3),new Coordinate(4,4),Color.BLACK));
		System.out.println("rook move " +board.move(new Coordinate(0,0),new Coordinate(0,4),Color.WHITE));
		System.out.println("black move "+board.move(new Coordinate(4,4),new Coordinate(3,5),Color.BLACK));
		System.out.println("rook move " +board.move(new Coordinate(0,4),new Coordinate(0,0),Color.WHITE));
		fields[6][4] = new Field(Color.WHITE,Type.ROOK);
		System.out.println("black move "+board.move(new Coordinate(3,5),new Coordinate(4,6),Color.BLACK));
		for (int i=0; i<8;i++){
			for (int j=0; j<8;j++){
				if (board.getGameBoard()[i][j] == null){
					System.out.print("[ ]");
				}else{
					System.out.print(board.getGameBoard()[i][j]);
				}
			}
			System.out.println();
		}
//        board.promotePawn(Color.BLACK,null);
	}
	@Test
	void contextLoads() {
	}

}
