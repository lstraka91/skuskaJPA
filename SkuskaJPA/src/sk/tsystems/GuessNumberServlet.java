package sk.tsystems;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.exceptions.GameException;
import sk.tsystems.gamestudio.exceptions.ScoreException;
import sk.tsystems.gamestudio.services.hibernate.GameServiceHibernateImpl;
import sk.tsystems.gamestudio.services.hibernate.PlayerServiceHibernateImpl;
import sk.tsystems.gamestudio.services.hibernate.ScoreServiceHibernateImpl;
import sk.tsystems.guessNumber.GuessNumber;

/**
 * Servlet implementation class GuessNumber
 */
@WebServlet("/GuessNumber")
public class GuessNumberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int steps;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GuessNumberServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		HttpSession session = request.getSession();
		if (session.getAttribute("steps") == null) {

			steps = 0;
			session.setAttribute("steps", steps);
		}

		GuessNumber guesNum = (GuessNumber) session.getAttribute("numbr");
		if (guesNum == null) {

			guesNum = new GuessNumber(20);

			session.setAttribute("numbr", guesNum);
		}

		try {
			int value = Integer.parseInt(request.getParameter("value"));

			if (value > guesNum.getToGuess()) {
				out.print("Try lower number");
				steps++;
				session.setAttribute("steps", steps);
			} else if (value < guesNum.getToGuess()) {
				out.print("Try bigger number");
				steps++;
				session.setAttribute("steps", steps);
			} else if (value == guesNum.getToGuess()) {
				guesNum.setGuessed(true);
			}
		} catch (Exception e) {
		}

		if (guesNum.isGuessed()) {
			out.println("<h1>Vyhral si</h1>");
			int score = (int) session.getAttribute("steps");
			addScore(score, request);

			guesNum = new GuessNumber(20);
			steps = 0;
			session.setAttribute("steps", steps);
			session.setAttribute("numbr", guesNum);
		}

		out.println("<div class='row'>");
		out.println("<div class='center-form panel'>");

		out.println("<form method='get'>");
		out.println("<div class='panel-body'>");
		out.println("<input type='hidden' name='action' value='play'>");
		out.println("<input type='hidden' name='name' value='GuessNumber'>");
		out.println("<div class='form-group'>");
		out.println(
				"Input number you think I am thinkig on:<input type='text' name='value' autofocus class='form-control input-lg'><br> ");
		out.println("</div>");
		out.println("<input type='submit'><br>");
		out.println("</div>");
		out.println("</form>");
		out.println("</div>");
		out.println("</div>");
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

	private void addScore(int score, HttpServletRequest req) {
		if (req.getSession().getAttribute("user") != null) {

			ScoreServiceHibernateImpl scoreImpl = new ScoreServiceHibernateImpl();
			Score scoreEntity = new Score();
			try {
				scoreEntity.setDate(new Date());
				scoreEntity.setGame(new GameServiceHibernateImpl().getGameByName("GuessNumber"));
				scoreEntity.setPlayer(new PlayerServiceHibernateImpl()
						.getPlayerFromDB((String) req.getSession().getAttribute("user")));
				scoreEntity.setScore(1000 / score);
				scoreImpl.add(scoreEntity);
			} catch (GameException e1) {
				e1.printStackTrace();
			} catch (ScoreException e) {

				e.printStackTrace();
			}

		}
	}
}
