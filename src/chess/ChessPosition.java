package chess;

import boardgame.Position;
import chess.exceptions.ChessException;

public class ChessPosition {

	private char column;
	private int row;

	public ChessPosition(char column, int row) throws ChessException {
		if (column < 'a' || column > 'z') {
			throw new ChessException("Error in constructor: Invalid column value.");
		}

		if (row < 0 || row > 7) {
			throw new ChessException("Error in constructor: Invalid row value.");

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

	protected Position toPosition() {
		int columnInt = (this.getColumn() - 'a');

		return new Position((8 - getRow()), columnInt);
	}

	protected ChessPosition toChessPosition(Position position) throws ChessException {
		return new ChessPosition((char)('a' + position.getColumn()), (8 - position.getRow()));
	}

}
