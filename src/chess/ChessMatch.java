package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board;
	
	public ChessMatch() {
		this.board = new Board(8,8);
		initialSetup();
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

    public void initialBlackSetup() {
		board.placePiece(new King(getBoard(), Color.BLACK), new Position(0,4));
		board.placePiece(new Rook(getBoard(), Color.BLACK) , new Position(0,0));
		board.placePiece(new Rook(getBoard(), Color.BLACK), new Position(0, 7));
    }
    
    public void initialWhiteSetup() {
    	board.placePiece(new King(getBoard(), Color.WHITE), new Position(7,3));
		board.placePiece(new Rook(getBoard(), Color.WHITE) , new Position(7,0));
		board.placePiece(new Rook(getBoard(), Color.WHITE), new Position(7, 7));
    }
    
	public void initialSetup() {
		initialBlackSetup();
		initialWhiteSetup();
		
	}
    
	
}
 