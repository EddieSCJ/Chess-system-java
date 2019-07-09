package boardgame;

import boardgame.exceptions.BoardException;

public abstract class Piece {

	protected Position position;
	protected Board board;
	
	public Piece(Board board) {
		this.board = board;
		this.position = null;
	}
	
	public Position getPosition() {
		return position;
	}
	
	protected Board getBoard() {
		return board;
	}
	public abstract boolean[][] possibleMoves() throws BoardException;
	
	public boolean possibleMove(Position position) throws BoardException {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	public boolean IsThereAnyPossibleMove() throws BoardException {
		boolean[][] possibleMoves = possibleMoves();
		
		for (int i = 0; i < possibleMoves.length; i++) {
			for (int j = 0; j < possibleMoves.length; j++) {
				if(possibleMoves[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	
}
