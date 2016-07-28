package sk.tsystems.pairsGame;

public class Field {
	private final int[][] tiles;
	private int rowCount;
	private int columnCount;
	private long startMillis;
	
	
	public Field(int rowCount, int columnCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		tiles = new int[rowCount][columnCount];
		generate();
		
		//shuffle();
		startMillis = System.currentTimeMillis();
	}


	private void generate() {
		// TODO Auto-generated method stub
		
	}
}
