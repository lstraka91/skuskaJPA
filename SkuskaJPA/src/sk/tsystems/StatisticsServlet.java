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

		request.setAttribute("commentsCount", statisticsServiceImpl.commentsCount());
		request.setAttribute("countOfGames", statisticsServiceImpl.countOfGames());
		request.setAttribute("countOfPlayers", statisticsServiceImpl.countOfPlayers());
		request.setAttribute("countOfTotalPlayedGames", statisticsServiceImpl.countOfTotalPlayedGames());
		request.setAttribute("gameCountScore", statisticsServiceImpl.gameCountScore());
		request.setAttribute("getCountRatings", statisticsServiceImpl.getCountRatings());
		request.setAttribute("getFavoriteGame", statisticsServiceImpl.getFavoriteGame().getGame());
		request.setAttribute("getMostCommentedGame", statisticsServiceImpl.getMostCommentedGame().getGameName());
		request.setAttribute("getPlayerGambler", statisticsServiceImpl.getPlayerGambler().getPlayer());
		request.setAttribute("getEachPlayerPlays", statisticsServiceImpl.getEachPlayerCountScore());
		request.setAttribute("countOfRating", statisticsServiceImpl.countOfRatings());
		request.setAttribute("countOfComments", statisticsServiceImpl.countOfComments());
		
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
