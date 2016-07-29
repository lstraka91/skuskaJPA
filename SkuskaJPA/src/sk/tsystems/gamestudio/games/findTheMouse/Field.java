package sk.tsystems.gamestudio.games.findTheMouse;

import java.util.Random;




public class Field {
	private final Tile[][] tiles;
	private int mouseRowPossition;
	private int mouseColumnPossition;
	/**
	 * Field row count. Rows are indexed from 0 to (rowCount - 1).
	 */
	private final int rowCount;

	/**
	 * Column count. Columns are indexed from 0 to (columnCount - 1).
	 */
	private final int columnCount;

	private boolean founded;
	
	private int turns;

	public Field(int rowCount, int columnCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		tiles = new Tile[rowCount][columnCount];
		founded = false;
		turns=0;
		generate();
	}

	public void openTile(int row, int column) {
		Tile tile = tiles[row][column];
		if (tile.getState() == Tile.State.CLOSED) {
			tile.setState(Tile.State.OPEN);
			turns++;

			if (tile instanceof Mouse) {
				setFounded(true);
				return;
			}
		}
	}

	/**
	 * Generates playing field.
	 */
	private void generate() {
		Random rndm = new Random();
		int randomColl = rndm.nextInt(getColumnCount());
		int randomRow = rndm.nextInt(getRowCount());
		if (getTile(randomRow, randomColl) == null) {
			tiles[randomRow][randomColl] = new Mouse();
			mouseRowPossition=randomRow;
			mouseColumnPossition= randomColl;
		}
		for (int row = 0; row < getRowCount(); row++) {
			for (int coll = 0; coll < getColumnCount(); coll++) {
				if (getTile(row, coll) == null) {
					tiles[row][coll] = new Nothing();
				}
			}
		}
	}

	public boolean isFounded() {
		return founded;
	}

	public void setFounded(boolean founded) {
		this.founded = founded;
	}

	public Tile getTile(int row, int column) {
		return tiles[row][column];
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public int getTurns() {
		return turns;
	}

	public void setTurns(int turns) {
		this.turns = turns;
	}

	public int getMouseRowPossition() {
		return mouseRowPossition;
	}

	public int getMouseColumnPossition() {
		return mouseColumnPossition;
	}


	

}
