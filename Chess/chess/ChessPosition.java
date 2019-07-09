package chess;

import boardgame.Position;
import chess.exceptions.ChessException;

public class ChessPosition {

	private char column;
	private int row;

	public ChessPosition(char column, int row) throws ChessException {
		if (column < 'a' || column > 'h') {
			throw new ChessException("Error in chessPosition constructor: Invalid column("+column+") value.");
		}

		if (row < 1 || row > 8) {
			throw new ChessException("Error in chessPosition constructor: Invalid row("+row+") value.");

		}
		this.row = row;
		this.column = column;
	}

	public char getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	protected  Position toPosition() {
		int columnInt = (this.getColumn() - 'a');

		return new Position((8 - getRow()), columnInt);
	}

	protected static ChessPosition toChessPosition(Position position) throws ChessException {
		return new ChessPosition((char)('a' + position.getColumn()), (8 - position.getRow()));
	}

	
	public String toString() {
		return ""+column+row;
	}
}
