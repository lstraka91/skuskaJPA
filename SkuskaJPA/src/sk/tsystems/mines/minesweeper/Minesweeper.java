package sk.tsystems.mines.minesweeper;

import sk.tsystems.mines.minesweeper.consoleui.ConsoleUI;
import sk.tsystems.mines.minesweeper.core.Field;

/**
 * Main application class.
 */
public class Minesweeper {
	/** User interface. */
	private UserInterface userInterface;

	private long startMillis;
	private BestTimes bestTimes;
	private Settings setting;

	public Settings getSetting() {
		return setting;
	}

	public void setSetting(Settings setting) {
		this.setting = setting;
		setting.save();
	}

	private static Minesweeper instance;

	public BestTimes getBestTimes() {
		return bestTimes;
	}

	/**
	 * Constructor.
	 */
	private Minesweeper() {
		instance = this;
		
		//setSetting(setting.load());
		setSetting(setting.BEGGINER);
		userInterface = new ConsoleUI();
		Field field = new Field(setting.getRowCount(), setting.getColumnCount(), setting.getMineCount());
		startMillis = System.currentTimeMillis();
		
		userInterface.newGameStarted(field);
	}

	/**
	 * Instance of Minesweeper / Singleton
	 * @return
	 */
	public static Minesweeper getInstance() {
		if (instance == null) {
			return new Minesweeper();

		} else {
			return instance;
		}
	}

	/**
	 * Get playing time
	 * 
	 * @return int in seconds of playing
	 */
	public int getPlayingSeconds() {
		int currentTime = (int) ((System.currentTimeMillis() - startMillis)/1000L);
		return currentTime;
	}

	/**
	 * Main method.
	 * 
	 * @param args
	 *            arguments
	 */
	public static void main(String[] args) {
		new Minesweeper();

	}
}
