package boardgame;

public class Position {

	private int row;
	private int column;
	
	public Position(int row, int column) {
		this.setColumn(column);
		this.setRow(row);
	}
	
	public int getRow() {
		return row;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}
	
	@Override
	public String toString() {
		return this.getRow()+
			  ", "+
			  this.getColumn();
	}
	
	
	
}
