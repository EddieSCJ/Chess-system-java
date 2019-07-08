package boardgame;

import boardgame.exceptions.BoardException;

public class Board {

	private int rowsQuantity;
	private int colunmsQuantity;
	private Piece pieces[][];

	public Board(int rowsQuantity, int columnsQuantity) throws BoardException {

		if (rowsQuantity < 1 || columnsQuantity < 1) {
			throw new BoardException("Error in board constructor: Invalid row or column quantity ("+columnsQuantity +", "+rowsQuantity+") value");
		}

		this.colunmsQuantity = columnsQuantity;
		this.rowsQuantity = rowsQuantity;
		pieces = new Piece[columnsQuantity][rowsQuantity];

	}

	public int getRowsQuantity() {
		return rowsQuantity;
	}

	public int getColunmsQuantity() {
		return colunmsQuantity;
	}

	public Piece piece(int row, int column) throws BoardException {
		if (!positionExists(row, column)) {
			throw new BoardException("Error in piece method: " + "This position ("+row+", "+column+")does not exist on this board.");
		}
		return pieces[row][column];
	}

	public Piece piece(Position position) throws BoardException {
		if (!positionExists(position)) {
			throw new BoardException("Error in piece method: " + "This position ("+position+")does not exist on this board.");
		}

		return pieces[position.getRow()][position.getColumn()];
	}

	public void placePiece(Piece piece, Position position) throws BoardException {
		
		if(thereIsAPiece(position)) {
			throw new BoardException("Error in place piece: there is already a piece("+piece+") on position("+position+")");
		}
		
		this.pieces[position.getRow()][position.getColumn()] = piece;
		piece.setPosition(position);
	}

	public boolean positionExists(int row, int column) {
		return row >= 0 && row < this.getRowsQuantity() && column >= 0 && column < this.getColunmsQuantity();
	}

	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}

	public boolean thereIsAPiece(Position position) throws BoardException {
		if(!positionExists(position)) {
			throw new BoardException("Error in method 'ThereIsAPiace': " + "This position ("+position+") does not exist on this board.");
		}

		return piece(position) != null;

		
	}
	
	public Piece removePiece(Position position) throws BoardException {
		if(!positionExists(position)) {
			throw new BoardException("This position("+position+") does not exist on this board.");
		}
		if(position == null) {
			return null;
		}
		
		Piece piece = piece(position);
		piece.setPosition(null);
		
		pieces[position.getRow()][position.getColumn()] = null;
		
		return piece;
	}

}
