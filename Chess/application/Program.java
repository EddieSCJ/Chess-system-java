package application;

import boardgame.exceptions.BoardException;
import chess.ChessMatch;
import chess.exceptions.ChessException;

public class Program {

	public static void main(String[] args) {
	try {
		ChessMatch chessMatch = new ChessMatch();
	
		UI.printBoard(chessMatch);
	}catch(BoardException be ) {
		System.out.println(be.getMessage());
	}catch(ChessException ce) {
		System.out.println(ce.getMessage());
	}
		
		
	}

}
