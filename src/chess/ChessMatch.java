package chess;

import boardgame.Board;
import boardgame.Position;
import boardgame.exceptions.BoardException;
import chess.exceptions.ChessException;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board;
	
	public ChessMatch() throws BoardException {
		this.board = new Board(8,8);
		initialSetup();
	}

	public Board getBoard() {
		return board;
	}

    public ChessPiece[][] getPieces() throws BoardException {

    	ChessPiece [][] chessPieces = new ChessPiece[this.getBoard().getRowsQuantity()][this.getBoard().getColunmsQuantity()];
    	
    	for (int i = 0; i < this.getBoard().getRowsQuantity(); i++)
    	{
    		for (int j = 0; j < this.getBoard().getColunmsQuantity(); j++) {
				chessPieces[i][j] = (ChessPiece) this.getBoard().piece(i, j);
			}
		}
    	
    	return chessPieces;
    }

    private void placeNewPiece(ChessPiece piece, char column, int row) throws BoardException, ChessException {
    	board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }
    
    
    private void initialBlackSetup() throws BoardException, ChessException {
		placeNewPiece(new King(getBoard(), Color.BLACK), 'a',4);
		placeNewPiece(new Rook(getBoard(), Color.BLACK) , 'a',0);
		placeNewPiece(new Rook(getBoard(), Color.BLACK), 'a', 7);
    }
    
    private void initialWhiteSetup() throws BoardException, ChessException {
    	placeNewPiece(new King(getBoard(), Color.WHITE), 'h',3);
		placeNewPiece(new Rook(getBoard(), Color.WHITE) , 'h',0);
		placeNewPiece(new Rook(getBoard(), Color.WHITE), 'h', 7);
    }
    
	private void initialSetup() throws BoardException, ChessException {
		initialBlackSetup();
		initialWhiteSetup();
		
	}
    
	
}
 