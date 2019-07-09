package chess.pieces;

import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import boardgame.exceptions.BoardException;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	public King(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "K";
	}

	@Override
	public boolean[][] possibleMoves() throws BoardException {
		// TODO Auto-generated method stub
		
		boolean possibleMoves[][] = new boolean[getBoard().getRowsQuantity()][getBoard().getColunmsQuantity()];
		
		Position position = new Position(0, 0);
		
		//above 
		
		position.setValues(this.position.getRow()-1, this.position.getColumn());
		if(getBoard().positionExists(position) && (!getBoard().thereIsAPiece(position) || isThereAnOpponentPiece(position))) {
			possibleMoves[position.getRow()][position.getColumn()]=true;
		}
		
		//right diagonal below
		
		position.setValues(this.position.getRow()-1, this.position.getColumn()+1);
		if(getBoard().positionExists(position) && (!getBoard().thereIsAPiece(position) || isThereAnOpponentPiece(position))) {
			possibleMoves[position.getRow()][position.getColumn()]=true;
		}
		
		//right diagonal above
		
		position.setValues(this.position.getRow()+1, this.position.getColumn()+1);
		if(getBoard().positionExists(position) && (!getBoard().thereIsAPiece(position) || isThereAnOpponentPiece(position))) {
			possibleMoves[position.getRow()][position.getColumn()]=true;
		}
				
		//left diagonal above
		
		position.setValues(this.position.getRow()+1, this.position.getColumn()-1);
		if(getBoard().positionExists(position) && (!getBoard().thereIsAPiece(position) || isThereAnOpponentPiece(position))) {
			possibleMoves[position.getRow()][position.getColumn()]=true;
		}		

		//left diagonal below
		
		position.setValues(this.position.getRow()-1, this.position.getColumn()-1);
		if(getBoard().positionExists(position) && (!getBoard().thereIsAPiece(position) || isThereAnOpponentPiece(position))) {
			possibleMoves[position.getRow()][position.getColumn()]=true;
		}		
		
		
		//below
		position.setValues(this.position.getRow()+1, this.position.getColumn());
		if(getBoard().positionExists(position) && (!getBoard().thereIsAPiece(position) || isThereAnOpponentPiece(position))) {
			possibleMoves[position.getRow()][position.getColumn()]=true;
		}
		//right
		position.setValues(this.position.getRow(), this.position.getColumn()+1);
		if(getBoard().positionExists(position) && (!getBoard().thereIsAPiece(position) || isThereAnOpponentPiece(position))) {
			possibleMoves[position.getRow()][position.getColumn()]=true;
		}
		
		//left
		position.setValues(this.position.getRow(), this.position.getColumn()-1);
		if(getBoard().positionExists(position) && (!getBoard().thereIsAPiece(position) || isThereAnOpponentPiece(position))) {
			possibleMoves[position.getRow()][position.getColumn()]=true;
		}
		
		
		return possibleMoves;
	}
	
	
}
