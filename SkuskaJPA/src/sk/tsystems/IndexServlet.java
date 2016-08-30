package sk.tsystems;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sk.tsystems.gamestudio.entity.Game;
import sk.tsystems.gamestudio.exceptions.GameException;
import sk.tsystems.gamestudio.services.hibernate.GameServiceHibernateImpl;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		initGames();
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
	}

	public void initGames() {
		try {
			Game findTheMouse = new GameServiceHibernateImpl().getGameByName("FindTheMouse");
			Game minesweeper = new GameServiceHibernateImpl().getGameByName("Minesweeper");
			Game npuzzle = new GameServiceHibernateImpl().getGameByName("NPuzzle");
			Game guessNumber = new GameServiceHibernateImpl().getGameByName("GuessNumber");
			if (findTheMouse == null) {
				Game game = new Game();
				game.setAuthor("Straka Lukas");
				game.setDateCreated(new Date());
				game.setGameName("FindTheMouse");
				game.setGameURL("/findTheMouse");
				new GameServiceHibernateImpl().add(game);
			} if (minesweeper == null) {
				Game game = new Game();
				game.setAuthor("Straka Lukas");
				game.setDateCreated(new Date());
				game.setGameName("Minesweeper");
				game.setGameURL("/mines");
				new GameServiceHibernateImpl().add(game);
			} if (npuzzle == null) {
				Game game = new Game();
				game.setAuthor("Straka Lukas");
				game.setDateCreated(new Date());
				game.setGameName("NPuzzle");
				game.setGameURL("/nPuzzle");
				new GameServiceHibernateImpl().add(game);
			} if (guessNumber == null) {
				Game game = new Game();
				game.setAuthor("Straka Lukas");
				game.setDateCreated(new Date());
				game.setGameName("GuessNumber");
				game.setGameURL("/guessNumber");
				new GameServiceHibernateImpl().add(game);
			}
		} catch (GameException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
