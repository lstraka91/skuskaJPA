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
import sk.tsystems.gamestudio.exceptions.PlayerException;
import sk.tsystems.gamestudio.exceptions.ScoreException;
import sk.tsystems.gamestudio.games.findTheMouse.Field;
import sk.tsystems.gamestudio.games.findTheMouse.Mouse;
import sk.tsystems.gamestudio.games.findTheMouse.Tile;
import sk.tsystems.gamestudio.services.hibernate.GameServiceHibernateImpl;
import sk.tsystems.gamestudio.services.hibernate.PlayerServiceHibernateImpl;
import sk.tsystems.gamestudio.services.hibernate.ScoreServiceHibernateImpl;

/**
 * Servlet implementation class FindTheMouseServlet
 */
@WebServlet("/FindTheMouse")
public class FindTheMouseServlet extends GamesServletServices {
	private static final long serialVersionUID = 1L;
	Tile tile;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		HttpSession session = request.getSession();

		Field field = (Field) session.getAttribute("findMouseField");
		if (field == null) {
			field = new Field(6, 6);
			session.setAttribute("findMouseField", field);
		}

		try {
			int row = Integer.parseInt(request.getParameter("row"));
			int column = Integer.parseInt(request.getParameter("column"));
			field.openTile(row, column);
		} catch (Exception e) {

		}

		renderField(out, field);
		if (request.getParameter("subject") != null) {
			if (request.getParameter("subject").equals("newGame")) {
				field = new Field(6, 6);
				session.setAttribute("findMouseField", field);
				// session.removeAttribute("playingTimeMouse");
			}
		}
		if (field.isFounded()) {
			// startPlayingTime = (long)
			// session.getAttribute("playingTimeMouse");
			// long duringTime = System.currentTimeMillis() - startPlayingTime;
			int score = (10000 / field.getTurns());
			out.println("<center><h2>You won this game!</h2>");
			out.println("<h3>Your score is: " + score + "</h3></center>");

			String gameName= request.getParameter("name");
			addScore(request, score, gameName);

			// session.removeAttribute("playingTimeMouse");
			field = new Field(6, 6);
			session.setAttribute("findMouseField", field);
			out.println("<div class='text-center'>");
			out.println("<div class='btn-group'>");
			out.println("<form method='get' class='center'>");
			out.println("<input type='hidden' name='action' value='play'>");
			out.println("<input type='hidden' name='name' value='FindTheMouse'>");
			out.println("<button name='subject' type='newGame' value='open' class='btn btn-primary'>New Game</button>");
			out.println("</form>");
			out.println("</div>");
			out.println("</div>");
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

	private void renderField(PrintWriter out, Field field) {
		out.println("<div class='text-center'>");
		out.println("<h3 class='center'> Turns " + field.getTurns() + "</h3> ");
		out.println("<table border='1' class='center'>");
		for (int row = 0; row < field.getRowCount(); row++) {
			out.println("<tr>");
			for (int column = 0; column < field.getColumnCount(); column++) {
				out.println("<td>");
				tile = field.getTile(row, column);
				if (tile.getState() == Tile.State.OPEN) {
					if (tile instanceof Mouse) {
						out.print("<img alt='openTile' src='images/mouse.png' style='width:100px'>");
					} else {
						out.print("<img alt='openTile' src='images/cheese.jpg' style='width:100px'>  ");
					}
				} else if (tile.getState() == Tile.State.CLOSED) {
					if(!field.isFounded()){
						
					out.printf(
							"<a href='?action=play&name=FindTheMouse&row=%d&column=%d'><img alt='backgrnd' src='images/backgroundMine.png' style='width:100px'></a> ",
							row, column);
					}else{
						out.printf(
								"<img alt='backgrnd' src='images/backgroundMine.png' style='width:100px'> ",
								row, column);
					}
				}
			}
		}
		out.println("</table>");
		out.println("</div>");
		System.out.println(field.getMouseRowPossition() + " " + field.getMouseColumnPossition());

	}

}
