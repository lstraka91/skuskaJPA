package sk.tsystems.mines.minesweeper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Settings implements Serializable {

	private final int rowCount;
	private final int columnCount;
	private final int mineCount;
	public static final Settings BEGGINER = new Settings(9, 9, 10);
	public static final Settings INTERMEDIATE = new Settings(16, 16, 40);
	public static final Settings EXPERT = new Settings(16, 30, 99);
	private static final String SETTING_FILE = System.getProperty("user.home")
			+ System.getProperty("file.separator") + "minesweeper.settings";

	/**
	 * Constructor Settings
	 * @param rowCount
	 * @param columnCount
	 * @param mineCount
	 */
	public Settings(int rowCount, int columnCount, int mineCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		this.mineCount = mineCount;
	}

	/**
	 * get row count of field
	 * 
	 * @return rowCoutn
	 */
	public int getRowCount() {
		return rowCount;
	}

	/**
	 * getter for column count of field
	 * 
	 * @return columnCount
	 */
	public int getColumnCount() {
		return columnCount;
	}

	/**
	 * get mine count of Field
	 * 
	 * @return mineCount
	 */
	public int getMineCount() {
		return mineCount;
	}

	/**
	 * override equals method
	 */
	@Override
	public boolean equals(Object o) {
		if (o.equals(this)) {
			return true;
		} else {

			return false;
		}
	}

	/**
	 * int hashCode function return row*column*mines
	 */
	@Override
	public int hashCode() {
		return getRowCount() * getColumnCount() * getMineCount();
	}

	/**
	 * Save the current setting game
	 * 
	 */
	public void save() {

		try {
			FileOutputStream fout = new FileOutputStream(SETTING_FILE);
			ObjectOutputStream output = new ObjectOutputStream(fout);
			output.writeObject(this);
			output.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Load the saved game
	 * 
	 * @return Setting object serialized in that file or new Begginer default
	 *         setting
	 */
	public static Settings load() {
		File file = new File(SETTING_FILE);
		Settings loaded = BEGGINER;
		if (file.exists()) {
			try {
				FileInputStream streamIn = new FileInputStream(file);

				ObjectInputStream objectInput = new ObjectInputStream(streamIn);
				loaded = (Settings) objectInput.readObject();
				objectInput.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return loaded;
	}
}
