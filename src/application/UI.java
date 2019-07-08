package application;

import boardgame.exceptions.BoardException;
import chess.ChessMatch;
import chess.ChessPiece;

public class UI {

	
	public static void printBoard(ChessMatch chessmatch ) throws BoardException {

		System.out.println("  __________________");
		for (int i = 0; i < chessmatch.getPieces().length; i++) {
		
			System.out.print((8-i)+"| ");
			
			for (int j = 0; j < chessmatch.getPieces().length; j++) {
				printPiece(chessmatch.getPieces()[i][j]);
			}
			System.out.println("|");
			//System.out.println(" |                                  |");
		}
		System.out.println("  ==================");
		System.out.println("   a b c d e f g h  ");
	}
	
	public static void printPiece(ChessPiece piece) {
		if(piece == null) {
			System.out.print("-");
		}else {
			System.out.print(piece);
		}
		System.out.print(" ");
	}
	
}
