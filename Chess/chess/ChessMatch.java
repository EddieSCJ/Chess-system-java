package chess;

import java.util.ArrayList;
import java.util.List;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import boardgame.exceptions.BoardException;
import chess.exceptions.ChessException;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board;
	private int turn;
	private Color currentPlayer;
	private List<Piece> piecesOnTheBoard = new ArrayList<Piece>();
	private List<Piece> capturedPieces = new ArrayList<Piece>();
	
	public ChessMatch() throws BoardException, ChessException {
		this.board = new Board(8,8);
		this.turn=1;
		this.currentPlayer=Color.WHITE;
		initialSetup();
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	public int getTurn() {
		return turn;
	}
	
	public Board getBoard() {
		return board;
	}

	public boolean[][] possibleMoves(ChessPosition sourcePosition) throws BoardException {
		
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		
		return getBoard().piece(position).possibleMoves();
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) throws BoardException {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		
			validateSourcePosition(source);
			validateTargetPosition(source,target);
			Piece capturedPiece = makeMove(source, target);
		
			nextTurn();
			
			return (ChessPiece) capturedPiece;
		
	}
	
	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer==Color.WHITE) ? Color.BLUE : Color.WHITE;
	}
	
	private void validateTargetPosition(Position source, Position target) throws ChessException, BoardException {
		if(!getBoard().piece(source).possibleMove(target)){
			throw new ChessException("This position is invalid, choose a valid position.");
		}
				
	}
	
	private void validateSourcePosition(Position source) throws BoardException {
		if(!board.thereIsAPiece(source)) {	
			throw new ChessException("Has no piece in source position("+source+").");
		}
		if(getCurrentPlayer() != ((ChessPiece) board.piece(source)).getColor()) {
			throw new ChessException("The chosen piece is not yours :D");
		}
		
		if(!board.piece(source).IsThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for this piece.");
		}
	}
	
	private Piece makeMove(Position source, Position target) throws BoardException {
		Piece piece = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target); 
		
		if(capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
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
    	piecesOnTheBoard.add(piece);
    }
    
    
    private void initialBlackSetup() throws BoardException, ChessException {
		placeNewPiece(new King(getBoard(), Color.BLUE), 'e',8);
		placeNewPiece(new Rook(getBoard(), Color.BLUE) , 'a',8);
		placeNewPiece(new Rook(getBoard(), Color.BLUE), 'h', 8);
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
 