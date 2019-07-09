package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import boardgame.exceptions.BoardException;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}
	
	public String toString() {
		return "R";
	}

	public boolean[][] possibleMoves() throws BoardException {
		// TODO Auto-generated method stub
		
		boolean possibleMoves[][] = new boolean[getBoard().getRowsQuantity()][getBoard().getColunmsQuantity()];
		
		Position position = new Position(0, 0);
		//above
		position.setValues(position.getRow()-1, position.getColumn());
		
		while(getBoard().positionExists(position) && !getBoard().thereIsAPiece(position)) {
		
			possibleMoves[position.getRow()][position.getColumn()] = true;
			position.setRow(position.getRow()-1);
	
		}
		
		if(getBoard().positionExists(position) && this.isThereAnOpponentPiece(position)) {
			possibleMoves[position.getRow()][position.getColumn()] = true;
		}
		
		//bellow
		position.setValues(position.getRow()+1, position.getColumn());
		
		while(getBoard().positionExists(position) && !getBoard().thereIsAPiece(position)) {
			possibleMoves[position.getRow()][position.getColumn()] = true;
			position.setRow(position.getRow()+1);
		}
		
		if(getBoard().positionExists(position) && this.isThereAnOpponentPiece(position)) {
			possibleMoves[position.getRow()][position.getColumn()] = true;
		}
		
		//right 
		
		position.setValues(position.getRow(), position.getColumn()+1);
		
		while(getBoard().positionExists(position) && !getBoard().thereIsAPiece(position)) {
			possibleMoves[position.getRow()][position.getColumn()] = true;
			position.setColumn(position.getColumn()+1);
		}
		
		if(getBoard().positionExists(position) && this.isThereAnOpponentPiece(position)) {
			possibleMoves[position.getRow()][position.getColumn()] = true;
		}
		
		// left
		
		position.setValues(position.getRow(), position.getColumn()-1);
		
		while(getBoard().positionExists(position) && !getBoard().thereIsAPiece(position)) {
			possibleMoves[position.getRow()][position.getColumn()] = true;
			position.setColumn(position.getColumn()-1);
		}
		
		if(getBoard().positionExists(position) && this.isThereAnOpponentPiece(position)) {
			possibleMoves[position.getRow()][position.getColumn()] = true;
		}
		
		
		return possibleMoves;
	}
	
	
}
