package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import boardgame.exceptions.BoardException;
import chess.exceptions.ChessException;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private boolean check;
	private Board board;
	private int turn;
	private Color currentPlayer;
	private List<Piece> piecesOnTheBoard = new ArrayList<Piece>();
	private List<Piece> capturedPieces = new ArrayList<Piece>();
	private boolean checkMate;
	
	
	public ChessMatch() throws BoardException, ChessException {
		this.board = new Board(8,8);
		this.turn=1;
		this.currentPlayer=Color.WHITE;
		initialSetup();
	}

	
	public boolean isCheckMate() {
		return checkMate;
	}


	public boolean isCheck() {
		return check;
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
	private Color opponent(Color color){
		return (color == Color.BLUE) ? Color.WHITE : Color.BLUE;
	}
	
	private boolean testCheckMate(Color color) throws BoardException {
		if (!checkTest(color)) {
			return false;
		}
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for (Piece p : list) {
			boolean[][] mat = p.possibleMoves();
			for (int i=0; i<board.getRowsQuantity(); i++) {
				for (int j=0; j<board.getColunmsQuantity(); j++) {
					if (mat[i][j]) {
						Position source = ((ChessPiece)p).getChessPosition().toPosition();
						Position target = new Position(i, j);
						Piece capturedPiece = makeMove(source, target);
						boolean testCheck = checkTest(color);
						undoMove(source, target, capturedPiece);
						if (!testCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}	
	
	private ChessPiece king(Color color) {
		List<Piece> kings = piecesOnTheBoard.stream().filter(N -> ((ChessPiece)N).getColor()==color).collect(Collectors.toList());
	
			for (Piece piece : kings) {
				if(piece instanceof King) {
					return (ChessPiece) piece;
				}
			}
		throw new IllegalStateException("DOES NOT EXIST THE KING "+color+" IN YOUR LIST");
	}

	private boolean checkTest(Color color) throws BoardException {
		Position kingPosition = king(color).getChessPosition().toPosition();
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(N -> ((ChessPiece)N).getColor()!= color).collect(Collectors.toList());
		
		for (Piece piece : opponentPieces) {
			if(piece.possibleMove(kingPosition)) {
				return true;
			}
		}
		
		return false;
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) throws BoardException {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		
			validateSourcePosition(source);
			validateTargetPosition(source,target);
			Piece capturedPiece = makeMove(source, target);
			
			if(checkTest(currentPlayer)) {
				undoMove(source, target, capturedPiece);
				throw new ChessException ("You can't put yourself in check");
			}
			
			this.check= (checkTest(opponent(currentPlayer))) ? true : false;
			
			if (testCheckMate(opponent(currentPlayer))) {
				checkMate = true;
			}
			else {
				nextTurn();
			}
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
			getCapturedPieces().add(capturedPiece);
		}
		board.placePiece(piece, target);
		
		return capturedPiece;
	}
	
	public void undoMove(Position source, Position target, Piece capturedPiece) throws BoardException {
		Piece piece = board.removePiece(target);
		board.placePiece(piece, source);
		
		if(capturedPiece != null)
		{
			board.placePiece(capturedPiece, target);
			getCapturedPieces().remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
		
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

	public List<Piece> getCapturedPieces() {
		return capturedPieces;
	}
    
	
	
}
 