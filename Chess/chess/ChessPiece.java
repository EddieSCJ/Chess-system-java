package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import boardgame.exceptions.BoardException;
import chess.exceptions.ChessException;

public abstract class ChessPiece extends Piece {

	private Color color;
	
	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	public ChessPosition getChessPosition() throws ChessException {
		return ChessPosition.toChessPosition(position);
	}
	public boolean isThereAnOpponentPiece(Position position) throws BoardException {
		ChessPiece piece = (ChessPiece) board.piece(position);
		
		return piece!= null && piece.getColor()!=color;
	}
	
}
