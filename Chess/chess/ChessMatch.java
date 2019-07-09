package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import boardgame.exceptions.BoardException;
import chess.exceptions.ChessException;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board;
	
	public ChessMatch() throws BoardException, ChessException {
		this.board = new Board(8,8);
		initialSetup();
	}

	public Board getBoard() {
		return board;
	}

	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) throws BoardException {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		
			validateSourcePosition(source);
			Piece capturedPiece = makeMove(source, target);
			
			return (ChessPiece) capturedPiece;
		
	}
	
	private void validateSourcePosition(Position source) throws BoardException {
		if(!board.thereIsAPiece(source)) {	
			throw new ChessException("Has no piece in source position("+source+").");
		}
		if(!board.piece(source).IsThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for this piece.");
		}
	}
	
	private Piece makeMove(Position source, Position target) throws BoardException {
		Piece piece = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target); 
		
		board.placePiece(piece, target);
		
		return capturedPiece;
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
		placeNewPiece(new King(getBoard(), Color.BLACK), 'e',8);
		placeNewPiece(new Rook(getBoard(), Color.BLACK) , 'a',8);
		placeNewPiece(new Rook(getBoard(), Color.BLACK), 'h', 8);
    }
    
    private void initialWhiteSetup() throws BoardException, ChessException {
    	placeNewPiece(new King(getBoard(), Color.WHITE), 'e',1);
		placeNewPiece(new Rook(getBoard(), Color.WHITE) , 'a',1);
		placeNewPiece(new Rook(getBoard(), Color.WHITE), 'h', 1);
    }
    
	private void initialSetup() throws BoardException, ChessException {
		initialBlackSetup();
		initialWhiteSetup();
		
	}
    
	
	
}
 