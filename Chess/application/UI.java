package application;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import boardgame.exceptions.BoardException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	public static void clearScreen() {
		System.out.println("\033[H\033[2J");
		System.out.flush();
		;
	}

	public static ChessPosition readChessPosition(Scanner dado) {
		try {
			String position = dado.nextLine();

			char column = position.charAt(0);
			int row = Integer.parseInt(position.substring(1));

			return new ChessPosition(column, row);
		} catch (Exception e) {
			throw new InputMismatchException("Invalid value, valid values: a1 to h8");
		}
	}

	public static void printMatch(ChessMatch chessMatch, List<ChessPiece> capturedPieces) throws BoardException {
		printBoard(chessMatch);
		System.out.println();
		printCapturedPieces(capturedPieces);
		System.out.println();
		System.out.println(ANSI_YELLOW+"Turn: "+ANSI_RESET+ANSI_RED+chessMatch.getTurn()+ANSI_RESET);
		if(chessMatch.getCurrentPlayer()==Color.WHITE) {
			System.out.println(ANSI_YELLOW+"Waiting player: "+ANSI_RESET+ANSI_WHITE+chessMatch.getCurrentPlayer()+ANSI_RESET);
		}else {
			System.out.println(ANSI_YELLOW+"Waiting player: "+ANSI_RESET+ANSI_BLUE+chessMatch.getCurrentPlayer()+ANSI_RESET);
		}
	}
	
	public static void printBoard(ChessMatch chessmatch, boolean possibleMoves[][]) throws BoardException {
		
		System.out.println("  __________________");
		for (int i = 0; i < chessmatch.getPieces().length; i++) {

			System.out.print(ANSI_WHITE+(8 - i)+ANSI_RESET + "| ");

			for (int j = 0; j < chessmatch.getPieces().length; j++) {
				printPiece(chessmatch.getPieces()[i][j], possibleMoves[i][j]);
			}
			System.out.println("|");
			// System.out.println(" | |");
		}
		System.out.println("  ==================");
		System.out.println(ANSI_WHITE+"   a b c d e f g h  "+ANSI_RESET);
	}

	public static void printBoard(ChessMatch chessmatch) throws BoardException {

		System.out.println("  __________________");
		for (int i = 0; i < chessmatch.getPieces().length; i++) {

			System.out.print(ANSI_WHITE+(8 - i)+ANSI_RESET + "| ");

			for (int j = 0; j < chessmatch.getPieces().length; j++) {
				printPiece(chessmatch.getPieces()[i][j], false);
			}
			System.out.println("|");
			// System.out.println(" | |");
		}
		System.out.println("  ==================");
		System.out.println(ANSI_WHITE+"   a b c d e f g h  "+ANSI_RESET);
	}

	public static void printCapturedPieces(List<ChessPiece> capturedPieces) {
		List<ChessPiece> white = capturedPieces.stream().filter(N -> N.getColor()==Color.WHITE).collect(Collectors.toList());
		List<ChessPiece> blue  = capturedPieces.stream().filter(N -> N.getColor()==Color.BLUE).collect(Collectors.toList());
		System.out.println();
		System.out.println("Captured pieces: ");
		System.out.println(ANSI_WHITE + "WHITE "+white.toString()+ANSI_RESET);
		System.out.println(ANSI_BLUE + "BLUE: "+blue.toString()+ANSI_RESET);
		
	}
	
	public static void printPiece(ChessPiece piece, boolean background) {
		if (background) {
			System.out.print(ANSI_PURPLE_BACKGROUND);
		}
		if (piece == null) {
			System.out.print("-" + ANSI_RESET);
		} else {
			if (piece.getColor() == Color.BLUE) {
				System.out.print(ANSI_BLUE + piece + ANSI_RESET);
			} else {
				System.out.print(ANSI_WHITE + piece + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}
}
