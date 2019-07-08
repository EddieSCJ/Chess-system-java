package boardgame;

import boardgame.exceptions.BoardException;

public class Board {

	private int rowsQuantity;
	private int colunmsQuantity;
	private Piece pieces[][];
	
	public Board(int rowsQuantity, int columnsQuantity) throws BoardException {
		
		if(rowsQuantity <1 || columnsQuantity <1) {
			throw new BoardException("Error in board creation: Invalid row or column value");
		}
		
		this.setColunmsQuantity(columnsQuantity);
		this.setRowsQuantity(rowsQuantity);
		pieces = new Piece[columnsQuantity][rowsQuantity];

	}
	
	public int getRowsQuantity() {
		return rowsQuantity;
	}
	public void setRowsQuantity(int rowsQuantity) {
		this.rowsQuantity = rowsQuantity;
	}
	public int getColunmsQuantity() {
		return colunmsQuantity;
	}
	public void setColunmsQuantity(int colunmsQuantity) {
		this.colunmsQuantity = colunmsQuantity;
	}
	
	public Piece piece(int row, int column) {
		return pieces[row][column];
	}
	
	public Piece piece(Position position) {
		return pieces[position.getRow()][position.getColumn()];
	}
	
	public void placePiece(Piece piece, Position position) {
		this.pieces[position.getRow()][position.getColumn()] = piece;
		piece.setPosition(position);
	}
	
	public boolean positionExists(int row, int column) {
		return row>=0 && row<this.getRowsQuantity() 
			&& column>=0 && column < this.getColunmsQuantity();
	}
	
	public boolean positionsExists(Position position) {
		return positionExists(position.getRow(),position.getColumn());
		}
	

	public boolean thereIsAPiece(Position position) {
		return piece(position) != null && positionsExists(position)==true;
	}	
	

	
	
	
	
}
