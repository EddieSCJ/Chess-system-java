package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import boardgame.exceptions.BoardException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.exceptions.ChessException;

public class Program {

	
	public static void main(String[] args) {
	
		Scanner dado = new Scanner(System.in);
		ChessMatch chessMatch = null;
		try {
		
			chessMatch = new ChessMatch();
		
		}
		catch(ChessException ce) {
			System.out.println(ce.getMessage());
			System.out.println();
		}
		catch(BoardException be) {
			System.out.println(be.getMessage());
			System.out.println();
		}
		
		while(true) {
			try {
    			UI.clearScreen();
    			UI.printBoard(chessMatch);
    			System.out.println();
    			
    			System.out.print("Source: " );
    			ChessPosition source = UI.readChessPosition(dado);
    			
    			System.out.print("Target: ");
    			ChessPosition target = UI.readChessPosition(dado);
    			
    			ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
    			
			}
			
			catch(BoardException be) {
				System.out.println(be.getMessage());
				dado.nextLine();
				System.out.println();
			}
			
			catch(InputMismatchException ime) {
				System.out.println(ime.getMessage());
				dado.nextLine();
				System.out.println();
				
			}
			
		}
		
	}

}
