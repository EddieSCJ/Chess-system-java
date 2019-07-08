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

    private void placeNewPiece(ChessPiece piece, int row, char column) throws BoardException, ChessException {
    	board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }
    
    
    private void initialBlackSetup() throws BoardException {
		board.placePiece(new King(getBoard(), Color.BLACK), new Position(0,4));
		board.placePiece(new Rook(getBoard(), Color.BLACK) , new Position(0,0));
		board.placePiece(new Rook(getBoard(), Color.BLACK), new Position(0, 7));
    }
    
    private void initialWhiteSetup() throws BoardException {
    	board.placePiece(new King(getBoard(), Color.WHITE), new Position(7,3));
		board.placePiece(new Rook(getBoard(), Color.WHITE) , new Position(7,0));
		board.placePiece(new Rook(getBoard(), Color.WHITE), new Position(7, 7));
    }
    
	private void initialSetup() throws BoardException {
		initialBlackSetup();
		initialWhiteSetup();
		
	}
    
	
}
 