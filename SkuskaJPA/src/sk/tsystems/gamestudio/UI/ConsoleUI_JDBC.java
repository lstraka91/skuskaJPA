package sk.tsystems.gamestudio.UI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import sk.tsystems.gamestudio.ServiceInterface;
import sk.tsystems.gamestudio.entity.Comment;
import sk.tsystems.gamestudio.entity.Game;
import sk.tsystems.gamestudio.entity.Player;
import sk.tsystems.gamestudio.entity.RatingId;
import sk.tsystems.gamestudio.entity.Rating;
import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.exceptions.CommentException;
import sk.tsystems.gamestudio.exceptions.GameException;
import sk.tsystems.gamestudio.exceptions.PlayerException;
import sk.tsystems.gamestudio.exceptions.RatingException;
import sk.tsystems.gamestudio.exceptions.ScoreException;
import sk.tsystems.gamestudio.games.GameInterface;
import sk.tsystems.gamestudio.services.jdbc.CommentServiceJDBCImpl;
import sk.tsystems.gamestudio.services.jdbc.GameServiceJDBCImpl;
import sk.tsystems.gamestudio.services.jdbc.PlayerServiceJDBCImpl;
import sk.tsystems.gamestudio.services.jdbc.RatingServiceJDBCImpl;
import sk.tsystems.gamestudio.services.jdbc.ScoreServiceJDBCImpl;

public class ConsoleUI_JDBC implements ServiceInterface {

	private List<Game> games;
	private GameServiceJDBCImpl gameImpl;
	private ScoreServiceJDBCImpl score;
	private RatingServiceJDBCImpl rating;
	private CommentServiceJDBCImpl comment;
	private PlayerServiceJDBCImpl player;

	public ConsoleUI_JDBC() {
		gameImpl = new GameServiceJDBCImpl();
		score = new ScoreServiceJDBCImpl();
		rating = new RatingServiceJDBCImpl();
		comment = new CommentServiceJDBCImpl();
		player = new PlayerServiceJDBCImpl();
	}

	@Override
	public void startGame(GameInterface gameIntrfc) {
		GameInterface game = gameIntrfc;
		String gameName = game.getClass().getSimpleName();
		String playerName="Valibuk";
		showScore(gameName);
		game.run();
		try {
			addHighScore(game.getScore(), getPlayerByName(playerName), getGameIdentByName(gameName));
			getAverageRating(gameName);
			addRating(getPlayerByName(playerName), getGameIdentByName(gameName));
			addComment(getPlayerByName(playerName), getGameIdentByName(gameName));
		} catch (GameException e) {
			System.err.println("Error during load Game" + e);
		} catch (PlayerException e) {
			System.err.println("Error during load Player" + e);
		} catch (CommentException e) {
			
			e.printStackTrace();
		}
		showComments(gameName);
	}

	private Game getGameIdentByName(String gameName) throws GameException {
		Game gameByName = gameImpl.getGameByName(gameName);
		//int identGame = gameByName.getIdentGame();
		return gameByName;
	}

	private Player getPlayerByName(String playerName) throws PlayerException {
		Player player = this.player.getPlayerFromDB(playerName);
		return player;

	}

	private void showScore(String game) {
		score.setGame(game);
		System.out.println(score.toString());
	}

	private void showRating(String game) {
		rating.setGame(game);
		System.out.println(rating.toString());
	}

	private void showComments(String game) {
		comment.setGame(game);
		System.out.println(comment.toString());
	}

	/**
	 * In JDK 6 use Console class instead.
	 * 
	 * @see readLine()
	 */
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	private void addHighScore(int highScore, Player player, Game game) {

		if (highScore > 0) {
			try {
				Date date = new Date();
				Score score= new Score();
				score.setGame(game);
				score.setPlayer(player);
				score.setScore(1000/highScore);
				score.setDate(date);
				
				this.score.add(score);
			} catch (ScoreException e) {
				e.printStackTrace();
			}
		}
	}

	private void showHighScore() {
		try {
			for (Game game : gameImpl.getGameList()) {
				System.out.println("===========================================");
				System.out.println("High score table for game: " + game.getGameName());
				System.out.println("===========================================");
				showScore(game.getGameName());
			}
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showRatings() {
		try {
			for (Game game : gameImpl.getGameList()) {
				System.out.println("===========================================");
				System.out.println("RATING table for game: " + game.getGameName());
				System.out.println("===========================================");
				showRating(game.getGameName());
			}
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addRating(Player player, Game game) {
		try {
			rating.selectPlayerRating(player, game);
		} catch (RatingException e1) {
			e1.printStackTrace();
		}
		if (rating.getPlayerRate() > 0) {
			System.out.println("You allready rate the game :" + rating.getPlayerRate());

		}
		System.out.println("Do you want to change the rating? ");
		if (doYouWant()) {
			if (rating.getPlayerRate() > 0) {
				try {
					rating.deleteRating(player, game);
				} catch (RatingException e) {
					e.printStackTrace();
				}
			}

			System.out.println("Enter rating 1-5");
			Scanner ratingScanner = new Scanner(System.in);
			if (ratingScanner.hasNextInt()) {
				int rating = ratingScanner.nextInt();
				if (rating > 0 && rating <= 5) {
					Date date = new Date();
					try {
						Rating ratingToAdd=new Rating();
						RatingId rid= new RatingId();
						rid.setGameId(game.getIdentGame());
						rid.setPlayer(player.getId());
						ratingToAdd.setRateDate(date);
						ratingToAdd.setRating(rating);
						ratingToAdd.setRatingId(rid);
						
						this.rating.add(ratingToAdd);
					} catch (RatingException e) {
						e.printStackTrace();
					}
				}
			} else {
				System.out.println("You enter wrong rating!! Input 1-5!!!");
			}
		}
	}

	private void getAverageRating(String gameName) {
		try {
			rating.averageRate(gameName);
			System.out.printf("%s, %s \n", "Count of Ratings", "Average Rating");
			System.out.printf("     %2d ,      %.2f \n", this.rating.getCountOfRatings(), this.rating.getAvgRating());
		} catch (RatingException e) {
			e.printStackTrace();
		}

	}

	private boolean doYouWant() {
		System.out.println("Type y/n");
		boolean condition = true;
		Scanner scan = new Scanner(System.in);
		do {

			if (scan.hasNext("y|Y")) {
				condition = false;

				return true;
			} else if (scan.hasNext("n|N")) {
				condition = false;

				return false;

			} else {
				System.out.println("Bad input..type Y/N");
				scan = new Scanner(System.in);

			}
		} while (condition);
		return false;
	}

	private void addComment(Player player, Game game) throws CommentException {
		Date date = new Date();
		System.out.println("Do you want to comment the game?");
		if (doYouWant() == true) {

			System.out.println("Enter the comment: ");
			Scanner read = new Scanner(System.in);
			String userComment = read.nextLine();
			
				Comment commentToAdd = new Comment();
				commentToAdd.setUserComment(userComment);
				commentToAdd.setGame(game);
				commentToAdd.setDateCommented(new java.sql.Date(date.getTime()));
				commentToAdd.setPlayer(player);
				this.comment.add(commentToAdd);
			
		}

	}
}
