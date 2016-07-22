package sk.tsystems.gamestudio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



import sk.ness.jpa.JpaHelper;
import sk.tsystems.gamestudio.games.guessNumber.GuessNumber;
import sk.tsystems.gamestudio.games.minesweeper.Minesweeper;
import sk.tsystems.gamestudio.games.nPuzzle.NPuzzle;

public class GameStudioConsoleUI implements UserIntefaceGameStudio{
	ServiceInterface serviceInstance;
	
	
	public GameStudioConsoleUI(ServiceInterface serviceInstance) {
		this.serviceInstance=serviceInstance;
	}

	private enum MenuOption {
		PLAY, SHOW_HIGH_SCORE, RATING, EXIT
	};

	private enum Games {
		MINESWEEPER, N_PUZZLE, GUESS_NUMBER, GO_BACK
	};
	
	@Override
	public void run() {
		while (true) {
			switch (showMenu()) {
			case PLAY:
				playGame();
				break;
			case SHOW_HIGH_SCORE:
				//showHighScore();
				break;
			case RATING:
				//showRatings();
				break;
			case EXIT:
				JpaHelper.closeAll();
				return;
			}
		}
		
	}
	
	private MenuOption showMenu() {
		System.out.println("-----------------------------------------------");
		System.out.println("--------Welcome to my Game-Center-Studio-------");
		for (MenuOption option : MenuOption.values()) {
			System.out.printf("%d. %s%n", option.ordinal() + 1, option);
		}
		System.out.println("-----------------------------------------------");

		int selection = -1;
		do {
			System.out.println("Option: ");
			selection = Integer.parseInt(readLine());
		} while (selection <= 0 || selection > MenuOption.values().length);

		return MenuOption.values()[selection - 1];
	}

	private void playGame() {
		
		System.out.println("Choose game you want to Play");
		for (Games games : Games.values()) {
			System.out.printf("%d. %s%n", games.ordinal() + 1, games);
		}
		System.out.println("-----------------------------------------------");

		int selection = -1;
		do {
			System.out.println("Game Index: ");
			selection = Integer.parseInt(readLine());
		} while (selection <= 0 || selection > Games.values().length);
		switch (selection) {
		case 1:
			serviceInstance.startGame(new Minesweeper());
			break;
		case 2:
			serviceInstance.startGame(new NPuzzle());
			break;
		case 3:
			serviceInstance.startGame(new GuessNumber(20));
			break;
		case 4:
			return;
		default:
			break;
		}

	}

	/**
	 * In JDK 6 use Console class instead.
	 * 
	 * @see readLine()
	 */
	private BufferedReader input = new BufferedReader(new InputStreamReader(
			System.in));

	/**
	 * Reads line from console.
	 * 
	 * @return the string from console
	 */
	private String readLine() {
		// In JDK 6.0 and above Console class can be used
		// return System.console().readLine();

		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}
	
}
