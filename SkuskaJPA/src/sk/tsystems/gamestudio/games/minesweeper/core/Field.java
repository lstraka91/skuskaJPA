package sk.tsystems.gamestudio.games.minesweeper.core;

import java.util.Random;

import sk.tsystems.gamestudio.games.minesweeper.core.Tile.State;

/**
 * Field represents playing field and game logic.
 */
public class Field {
	/**
	 * Playing field tiles.
	 */
	private final Tile[][] tiles;

	/**
	 * Field row count. Rows are indexed from 0 to (rowCount - 1).
	 */
	private final int rowCount;

	/**
	 * Column count. Columns are indexed from 0 to (columnCount - 1).
	 */
	private final int columnCount;

	/**
	 * Mine count.
	 */
	private final int mineCount;

	/**
	 * Game state.
	 */
	private GameState state = GameState.PLAYING;

	/**
	 * Constructor.
	 *
	 * @param rowCount
	 *            row count
	 * @param columnCount
	 *            column count
	 * @param mineCount
	 *            mine count
	 */
	public Field(int rowCount, int columnCount, int mineCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		this.mineCount = mineCount;
		tiles = new Tile[rowCount][columnCount];

		// generate the field content
		generate();
	}

	/**
	 * Opens tile at specified indeces.
	 *
	 * @param row
	 *            row number
	 * @param column
	 *            column number
	 */
	public void openTile(int row, int column) {
		Tile tile = tiles[row][column];
		if (tile.getState() == Tile.State.CLOSED) {
			tile.setState(Tile.State.OPEN);

			if (tile instanceof Mine) {
				setState(GameState.FAILED);
				return;
			} else if (tile instanceof Clue) {
				if (((Clue) tile).getValue() == 0) {
					openAdjacentTiles(row, column);
				}
			}

			if (isSolved()) {
				setState(GameState.SOLVED);
				return;
			}
		}
	}

	/**
	 * Marks tile at specified indeces.
	 *
	 * @param row
	 *            row number
	 * @param column
	 *            column number
	 */
	public void markTile(int row, int column) {
		Tile tile = getTile(row, column);
		if (tile.getState() == Tile.State.CLOSED) {
			tile.setState(Tile.State.MARKED);
		} else if (tile.getState() == Tile.State.MARKED) {
			tile.setState(Tile.State.CLOSED);
		}
	}

	/**
	 * Generates playing field.
	 */
	private void generate() {

		generateMines();
		for (int row = 0; row < getRowCount(); row++) {
			for (int coll = 0; coll < getColumnCount(); coll++) {
				if (getTile(row, coll) == null) {
					tiles[row][coll] = new Clue(countAdjacentMines(row, coll));
				}
			}
		}
	}

	/**
	 * Generates random position for mines
	 */
	private void generateMines() {
		for (int i = 0; i < getMineCount(); i++) {
			boolean cycle = true;
			do {

				Random rndm = new Random();
				int randomColl = rndm.nextInt(getColumnCount());
				int randomRow = rndm.nextInt(getRowCount());

				if (getTile(randomRow, randomColl) == null) {
					tiles[randomRow][randomColl] = new Mine();
					cycle = false;
				}
			} while (cycle);

		}
	}

	/**
	 * Returns true if game is solved, false otherwise.
	 *
	 * @return true if game is solved, false otherwise
	 */
	private boolean isSolved() {
		if (getColumnCount() * getRowCount() - getNumberof(State.OPEN) == getMineCount()) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Returns count of tile state objects
	 * 
	 * @param state
	 * @return count of objects
	 */
	private int getNumberof(Tile.State state) {
		int count = 0;
		for (int row = 0; row < getRowCount(); row++) {
			for (int column = 0; column < getColumnCount(); column++) {
				if (getTile(row, column).getState() == state) {
					count++;
				}
			}
		}
		return count;

	}

	/**
	 * Returns number of adjacent mines for a tile at specified position in the
	 * field.
	 *
	 * @param row
	 *            row number.
	 * @param column
	 *            column number.
	 * @return number of adjacent mines.
	 */
	private int countAdjacentMines(int row, int column) {
		int count = 0;
		for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
			int actRow = row + rowOffset;
			if (actRow >= 0 && actRow < getRowCount()) {
				for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
					int actColumn = column + columnOffset;
					if (actColumn >= 0 && actColumn < getColumnCount()) {
						if (tiles[actRow][actColumn] instanceof Mine) {
							count++;
						}
					}
				}
			}
		}

		return count;
	}

	/**
	 * Open tiles with Clue value 0
	 * 
	 * @param row
	 *            row number.
	 * @param column
	 *            column number
	 */
	private void openAdjacentTiles(int row, int column) {
		for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
			int actRow = row + rowOffset;
			if (actRow >= 0 && actRow < getRowCount()) {
				for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
					int actColumn = column + columnOffset;
					if (actColumn >= 0 && actColumn < getColumnCount()) {
						if (tiles[actRow][actColumn] instanceof Clue) {
							// if (((Clue) tiles[actRow][actColumn]).getValue()
							// <= 1) {
							openTile(actRow, actColumn);
							// }
						}
					}
				}
			}
		}
	}

	/**
	 * Return current tile on the row and column position
	 * 
	 * @param row
	 *            - row
	 * @param column
	 *            - column
	 * @return current tile on the posistion
	 */
	public Tile getTile(int row, int column) {
		return tiles[row][column];
	}

	/**
	 * Getter function for rows count
	 * 
	 * @return count of rows
	 */
	public int getRowCount() {
		return rowCount;
	}

	/**
	 * Getter function for columns count
	 * 
	 * @return count of columns
	 */
	public int getColumnCount() {
		return columnCount;
	}

	/**
	 * Getter for mines count
	 * 
	 * @return count of mines
	 */
	public int getMineCount() {
		return mineCount;
	}

	/**
	 * Getter for current game state
	 * 
	 * @return state of game
	 */
	public GameState getState() {
		return state;
	}

	/**
	 * Setter for game state
	 * 
	 * @param state
	 *            set that state as game state
	 */
	public void setState(GameState state) {
		this.state = state;
	}
	
	public int getRemainingMineCount(){
		return getMineCount()-getNumberof(State.MARKED);
	}
}
