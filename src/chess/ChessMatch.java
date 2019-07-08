package chess;

import boardgame.Board;

public class ChessMatch {

	private Board board;
	
	public ChessMatch() {
		this.board = new Board(8,8);
	}

	public Board getBoard() {
		return board;
	}

    public ChessPiece[][] getPieces() {

    	ChessPiece [][] chessPieces = new ChessPiece[this.getBoard().getRows()][this.getBoard().getColunms()];
    	
    	for (int i = 0; i < this.getBoard().getRows(); i++)
    	{
    		for (int j = 0; j < this.getBoard().getColunms(); j++) {
				chessPieces[i][j] = (ChessPiece) this.getBoard().piece(i, j);
			}
		}
    	
    	return chessPieces;
    }

	
	
}
