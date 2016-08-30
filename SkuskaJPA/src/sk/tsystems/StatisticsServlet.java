package sk.tsystems;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sk.tsystems.gamestudio.services.hibernate.StatisticsHibernateImpl;

/**
 * Servlet implementation class StatisticsServlet
 */
@WebServlet("/statistics")
public class StatisticsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StatisticsHibernateImpl statisticsServiceImpl;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		statisticsServiceImpl = new StatisticsHibernateImpl();
		if (statisticsServiceImpl.commentsCount() != null) {
			request.setAttribute("commentsCount", statisticsServiceImpl.commentsCount());
		}
		if (statisticsServiceImpl.countOfGames() != 0) {
			request.setAttribute("countOfGames", statisticsServiceImpl.countOfGames());
		}
		if (statisticsServiceImpl.countOfPlayers() != 0) {
			request.setAttribute("countOfPlayers", statisticsServiceImpl.countOfPlayers());
		}
		if (statisticsServiceImpl.getCountRatings() != null) {
			request.setAttribute("getCountRatings", statisticsServiceImpl.getCountRatings());
		}
		if (statisticsServiceImpl.countOfTotalPlayedGames() != 0) {
			request.setAttribute("countOfTotalPlayedGames", statisticsServiceImpl.countOfTotalPlayedGames());

		}
		if (statisticsServiceImpl.gameCountScore() != null) {
			request.setAttribute("gameCountScore", statisticsServiceImpl.gameCountScore());
		}
		if (statisticsServiceImpl.getFavoriteGame() != null) {
			request.setAttribute("getFavoriteGame", statisticsServiceImpl.getFavoriteGame().getGame());

		}
		if (statisticsServiceImpl.getMostCommentedGame() != null) {
			request.setAttribute("getMostCommentedGame", statisticsServiceImpl.getMostCommentedGame().getGameName());

		}
		if (statisticsServiceImpl.getPlayerGambler() != null) {

			request.setAttribute("getPlayerGambler", statisticsServiceImpl.getPlayerGambler().getPlayer());
		}
		if (statisticsServiceImpl.getEachPlayerCountScore() != null) {
			request.setAttribute("getEachPlayerPlays", statisticsServiceImpl.getEachPlayerCountScore());

		}
		if (statisticsServiceImpl.countOfRatings() != 0) {
			request.setAttribute("countOfRating", statisticsServiceImpl.countOfRatings());

		}
		if (statisticsServiceImpl.countOfComments() != 0) {
			request.setAttribute("countOfComments", statisticsServiceImpl.countOfComments());

		}

		request.getRequestDispatcher("/WEB-INF/jsp/statistic.jsp").forward(request, response);
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
