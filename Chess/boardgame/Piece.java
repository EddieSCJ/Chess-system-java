package boardgame;

public class Piece {

	private Position position;
	protected Board board;
	
	public Piece(Board board) {
		this.board = board;
		this.setPosition(null);
	}
	
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	protected Board getBoard() {
		return board;
	}
	
	
	
}