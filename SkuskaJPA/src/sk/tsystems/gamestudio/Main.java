package sk.tsystems.gamestudio;

import sk.tsystems.gamestudio.UI.ConsoleUI_JDBC;
import sk.tsystems.gamestudio.UI.ConsoleUIHibernate;
import sk.tsystems.gamestudio.services.jdbc.ScoreServiceJDBCImpl;

public class Main {

	public static void main(String[] args) {

		// new GuessNumber(50);
//		Games g = new Games();
//		g.addGame(new Game(1, "Minesweeper"));
//		g.addGame(new Game(2, "nPuzzle"));
//		g.addGame(new Game(3, "Guess The Number"));
//		UserIntefaceGameStudio ui = new ConsoleUIGameStudio(g);
		UserIntefaceGameStudio ui = new GameStudioConsoleUI(new ConsoleUIHibernate());
//		UserIntefaceGameStudio ui = new GameStudioConsoleUI(new ConsoleUI_JDBC());
		ui.run();


		
	}	

}
