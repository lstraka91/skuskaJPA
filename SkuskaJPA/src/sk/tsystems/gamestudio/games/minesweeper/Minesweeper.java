package sk.tsystems.gamestudio.games.minesweeper;

import sk.tsystems.gamestudio.games.GameInterface;
import sk.tsystems.gamestudio.games.minesweeper.consoleui.ConsoleUI;
import sk.tsystems.gamestudio.games.minesweeper.core.Field;

/**
 * Main application class.
 */
public class Minesweeper implements Runnable, GameInterface {
	/** User interface. */
	private UserInterface userInterface;
	private long startMillis;
	private BestTimes bestTimes;
	private Settings setting;
	private Field field;
	private int score;

	@Override
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

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
	public Minesweeper() {
		instance = this;

		// setSetting(setting.load());
		setSetting(setting.BEGGINER);
		userInterface = new ConsoleUI();
		field = new Field(setting.getRowCount(), setting.getColumnCount(),
				setting.getMineCount());
		startMillis = System.currentTimeMillis();
		this.score = 0;
		// userInterface.newGameStarted(field);
	}

	@Override
	public void run() {
		userInterface.newGameStarted(field);

	}

	/**
	 * Instance of Minesweeper / Singleton
	 * 
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
		int currentTime = (int) ((System.currentTimeMillis() - startMillis) / 1000L);
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
