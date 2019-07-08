package application;

import chess.ChessMatch;
import chess.ChessPiece;

public class UI {

	
	public static void printBoard(ChessMatch chessmatch ) {

		System.out.println(" ____________________________________");
		for (int i = 0; i < chessmatch.getPieces().length; i++) {
		
			System.out.print((8-i)+"| ");
			
			for (int j = 0; j < chessmatch.getPieces().length; j++) {
				printPiece(chessmatch.getPieces()[i][j]);
			}
			System.out.print(" |");
			System.out.println();
			System.out.println(" |                                  |");
		}
		System.out.println(" ====================================");
		System.out.println("    a   b   c   d   e   f   g   h    ");
	}
	
	public static void printPiece(ChessPiece piece) {
		if(piece == null) {
			System.out.print(" - ");
		}else {
			System.out.println(" "+piece+" ");
		}
		System.out.print(" ");
	}
	
}
