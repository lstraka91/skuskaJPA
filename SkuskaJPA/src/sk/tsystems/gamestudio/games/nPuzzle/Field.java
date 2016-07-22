package sk.tsystems.gamestudio.games.nPuzzle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

public class Field implements Serializable {

	private Tile[][] tiles;
	private final int rowCount;
	private final int columnCount;
	private final int puzzleCount;
	private GameState state = GameState.PLAYING;

	public void setState(GameState state) {
		this.state = state;
	}

	public File getSaveGameFile() {
		return saveGameFile;
	}

	private File saveGameFile = new File("savedGame.bin");

	public Field(int rowCount, int columnCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		this.puzzleCount = rowCount * columnCount - 1;
		this.tiles = new Tile[rowCount][columnCount];
		state = GameState.PLAYING;
		generate();
	}

	private void generate() {
		int value = 1;
		for (int row = 0; row < getRowCount(); row++) {
			for (int coll = 0; coll < getColumnCount(); coll++) {
				if (getTile(row, coll) == null && value <= getPuzzleCount()) {
					tiles[row][coll] = new Stone(value);
					value++;
				}
			}
		}
		shuffle();
	}

	private void shuffle() {
		for (int i = 0; i < 50; i++) {

			Random rndm = new Random();

			int rnd = rndm.nextInt(4);
			switch (rnd) {
			case 0:
				moveTile(Directions.UP);
				moveTile(Directions.LEFT);
				break;
			case 1:
				moveTile(Directions.DOWN);
				moveTile(Directions.RIGHT);
				break;
			case 2:
				moveTile(Directions.LEFT);
				moveTile(Directions.DOWN);
				break;
			case 3:
				moveTile(Directions.RIGHT);
				moveTile(Directions.UP);
			default:
				break;
			}
		}
	}

	private boolean isSolved() {
		int value = 0;
		boolean isSolv = false;
		for (int i = 0; i < getRowCount(); i++) {
			for (int j = 0; j < getColumnCount(); j++) {
				value++;
				if (getTile(i, j) instanceof Stone) {

					if (((Stone) getTile(i, j)).getValue() != value) {
						return false;
					}
				}
			}
		}
		if (value - 1 == getPuzzleCount()) {
			isSolv = true;
		}
		return isSolv;
	}

	public void moveTile(Directions direction) {

		for (int row = 0; row < getRowCount(); row++) {
			for (int column = 0; column < getColumnCount(); column++) {
				if (getTile(row, column) == null) {
					switch (direction) {
					case UP:
						if (row + 1 < getRowCount()) {
							tiles[row][column] = getTile(row + 1, column);
							tiles[row + 1][column] = null;
						}
						break;
					case DOWN:
						if (row - 1 >= 0) {
							tiles[row][column] = getTile(row - 1, column);
							tiles[row - 1][column] = null;
						}
						break;
					case RIGHT:
						if (column - 1 >= 0) {
							tiles[row][column] = getTile(row, column - 1);
							tiles[row][column - 1] = null;
						}
						break;
					case LEFT:
						if (column + 1 < getColumnCount()) {
							tiles[row][column] = getTile(row, column + 1);
							tiles[row][column + 1] = null;
						}
						break;
					default:
						break;
					}
					if (isSolved()) {
						state = GameState.SOLVED;
					}
					return;
				}
			}
		}

	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public int getPuzzleCount() {
		return puzzleCount;
	}

	public GameState getState() {
		return state;
	}

	public Tile getTile(int row, int column) {
		return tiles[row][column];
	}

	public void saveFile() throws IOException {
		ObjectOutputStream output = null;

		try {
			FileOutputStream fout = new FileOutputStream(saveGameFile);
			output = new ObjectOutputStream(fout);
			output.writeObject(this.tiles);
			output.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void loadFile() {
		ObjectInputStream objectInput = null;
		if (saveGameFile.exists()) {

			try {
				FileInputStream streamIn = new FileInputStream(saveGameFile);

				objectInput = new ObjectInputStream(streamIn);
				tiles = (Tile[][]) objectInput.readObject();
				objectInput.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			System.err.println("!!!! No such file " + saveGameFile + " !!!!");
		}

	}

	private Possition getEmptyPossition() {

		for (int i = 0; i < getRowCount(); i++) {
			for (int j = 0; j < getColumnCount(); j++) {
				if (getTile(i, j) == null) {
					return new Possition(i, j);
				}
			}
		}
		throw new RuntimeException("BAD");
	}
}
