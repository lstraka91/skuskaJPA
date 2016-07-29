package sk.ness.jpa;

import java.util.Date;

import sk.tsystems.gamestudio.UI.ConsoleUIHibernate;
import sk.tsystems.gamestudio.entity.Game;
import sk.tsystems.gamestudio.exceptions.GameException;
import sk.tsystems.gamestudio.games.guessNumber.GuessNumber;
import sk.tsystems.gamestudio.services.hibernate.GameServiceHibernateImpl;

public class TestHibernateUIConsole {

	public static void main(String[] args) {
//	ConsoleUIHibernate ui = new ConsoleUIHibernate();
	GameServiceHibernateImpl gameImpl= new GameServiceHibernateImpl();
	
//	ui.showSkore("guessNumber");
//
////	Hra game = new Hra();
////	game.setGameName("Minesweeper");
////	gameImpl.addGame(game);
//	ui.showAvgRateAndCount("guessNumber");
//	
//	ui.startGame(new GuessNumber(20));
//	
	Game game = new Game();
	game.setAuthor("Straky");
	game.setDateCreated(new Date());
	game.setGameName("FindTheMouse");
	game.setGameURL("/GameCener/FindTheMouse");
	try {
		gameImpl.add(game);
	} catch (GameException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
