package sk.tsystems.gamestudio.games.minesweeper.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sk.tsystems.gamestudio.games.minesweeper.BestTimes;
import sk.tsystems.gamestudio.games.minesweeper.Minesweeper;
import sk.tsystems.gamestudio.games.minesweeper.UserInterface;
import sk.tsystems.gamestudio.games.minesweeper.core.Clue;
import sk.tsystems.gamestudio.games.minesweeper.core.Field;
import sk.tsystems.gamestudio.games.minesweeper.core.GameState;
import sk.tsystems.gamestudio.games.minesweeper.core.Mine;
import sk.tsystems.gamestudio.games.minesweeper.core.Tile;
import sk.tsystems.gamestudio.games.minesweeper.core.Tile.State;

/**
 * Console user interface.
 */
public class ConsoleUI implements UserInterface {
	/** Playing field. */
	private Field field;
	private boolean isPlaying;
	
	
	public ConsoleUI() {
		isPlaying=true;
	}

	/** Input reader. */
	private BufferedReader input = new BufferedReader(new InputStreamReader(
			System.in));

	/**
	 * Reads line of text from the reader.
	 * 
	 * @return line as a string
	 */
	private String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * minesweeper.consoleui.UserInterface#newGameStarted(minesweeper.core.Field
	 * )
	 */
	@Override
	public void newGameStarted(Field field) {
		this.field = field;
		
		do {
			update();
			processInput();
			if (field.getState() == GameState.SOLVED) {
				update();
				System.out.println("You win!!");
				Minesweeper.getInstance().setScore(Minesweeper.getInstance().getPlayingSeconds());
				isPlaying=false;
			} else if (field.getState() == GameState.FAILED) {
				update();
				System.out.println("You loose this game.");
				//System.exit(0);
				isPlaying=false;
			}
		} while (isPlaying);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see minesweeper.consoleui.UserInterface#update()
	 */
	@Override
	public void update() {
		Tile tile;
		System.out.println("Remaining count of mines: "+field.getRemainingMineCount());
		System.out.println("Playing time: "+Minesweeper.getInstance().getPlayingSeconds());
		displayFirstRow();

		char rowChar = 'A';
		for (int row = 0; row < field.getRowCount(); row++) {
			System.out.print(rowChar + " ");
			rowChar++;
			for (int column = 0; column < field.getColumnCount(); column++) {
				tile = field.getTile(row, column);
				if (tile.getState() == State.OPEN) {
					if (tile instanceof Mine) {
						System.out.print("  X");
					} else if (tile instanceof Clue) {
						System.out.print("  " + (((Clue) tile).getValue()));
					}
				} else if (tile.getState() == State.CLOSED) {
					System.out.print("  -");
				} else if (tile.getState() == State.MARKED) {
					System.out.print("  M");
				}
			}
			System.out.println();
		}
	}

	private void displayFirstRow() {
		System.out.print("  ");
		for (int i = 0; i < field.getColumnCount(); i++) {
			System.out.printf("  %d", i);
		}
		System.out.println();
	}
	private void printBT(){
		BestTimes bt = new BestTimes();
		bt.addPlayerTime("vajda", 45613);
		bt.addPlayerTime("super", 451);
		bt.toString();
	}

	/**
	 * Processes user input. Reads line from console and does the action on a
	 * playing field according to input string.
	 */
	private void processInput() {
		System.out
				.println("Please enter your selection (X) EXIT, (MA2) MARK, (OA2) OPEN ");
		try {
			String input = readLine();
			handleInput(input);
		} catch (WrongFormatException e) {
			
			e.getMessage();
		}
	}

	private void handleInput(String input) throws WrongFormatException {
		
		Pattern patern = Pattern
				.compile("([Oo]|[Mm]|[xX])([A-Za-z]{1})?([0-9][0-9]?)?");
		Matcher matcher = patern.matcher(input);

		if (matcher.matches()) {

			if (matcher.group(1).equalsIgnoreCase("x")) {
				if (matcher.group(0).length() < 2) {
					System.out.println("Bye Bye !");
					isPlaying=false;
					return;
//					System.exit(0);
				} else {
					System.out.println("To exit type: 'X' or 'x' ");
					processInput();
				}
			} else if (matcher.group(0).length() >= 3
					&& matcher.group(2) != null) {
				char row = matcher.group(2).toUpperCase().charAt(0);
				int column = Integer.parseInt(matcher.group(3));
				if (matcher.group(1).equalsIgnoreCase("o")) {
					int rowMin = 65;
					int rowMax = 65 + field.getRowCount() - 1;
					char tempRowMax = (char) rowMax;
					char tempRowMin = (char) rowMin;
					if (row >= tempRowMin && row <= tempRowMax
							&& column < field.getColumnCount()) {

						field.openTile(row - 65, column);
					}else{
						System.out.println("Out of field range");
					}
				} else if (matcher.group(1).equalsIgnoreCase("m")) {
					int rowMin = 65;
					int rowMax = 65 + field.getRowCount() - 1;
					char tempRowMax = (char) rowMax;
					char tempRowMin = (char) rowMin;
					if (row >= tempRowMin && row <= tempRowMax
							&& column < field.getColumnCount()) {

						field.markTile(row - 65, column);
					}else{
						System.out.println("Out of field range");
					}
				}
			} else {
				System.out.println("You type something wrong try again");
				processInput();
			}
		} else {
			System.out.println("You type something wrong try again");
			processInput();
		}
	}
}
