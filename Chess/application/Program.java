package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import boardgame.Piece;
import boardgame.exceptions.BoardException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.exceptions.ChessException;

public class Program {

	public static void main(String[] args) throws BoardException {

		List<ChessPiece> capturedPieces = new ArrayList<ChessPiece>();
		Scanner dado = new Scanner(System.in);
		ChessMatch chessMatch = null;
		try {

			chessMatch = new ChessMatch();

		} catch (ChessException ce) {
			System.out.println(ce.getMessage());
			System.out.println();
		} catch (BoardException be) {
			System.out.println(be.getMessage());
			System.out.println();
		}

		while (!chessMatch.isCheckMate()) {
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch, capturedPieces);
				System.out.println();

				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(dado);

				UI.clearScreen();

				UI.printBoard(chessMatch, chessMatch.possibleMoves(source));

				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(dado);

				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				if (capturedPiece != null) {
					capturedPieces.add(capturedPiece);
				}
			}

			catch (BoardException be) {
				System.out.println(be.getMessage());
				System.out.println("Type ENTER");
				dado.nextLine();
			}

			catch (InputMismatchException ime) {
				System.out.println(ime.getMessage());
				System.out.println("Type ENTER");
				dado.nextLine();

			}

		}
		UI.clearScreen();
		UI.printMatch(chessMatch, capturedPieces);
		

	}

}
