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
import sk.tsystems.stones.core.Field;

/**
 * Servlet implementation class Npuzzle
 */
@WebServlet("/NPuzzle")
public class Npuzzle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private long startPlayingTime;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Npuzzle() {
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
		// session.removeAttribute("fild");
		if (session.getAttribute("playTime") == null) {

			startPlayingTime = System.currentTimeMillis();
			session.setAttribute("playTime", startPlayingTime);
		}
		Field field = (Field) session.getAttribute("fild");
		if (field == null) {

			field = new Field(3, 3);
			session.setAttribute("fild", field);
		}
		if (request.getParameter("subject") != null && request.getParameter("subject").equals("restart")) {
			session.removeAttribute("playTime");
			field = new Field(3, 3);
			session.setAttribute("fild", field);
		}

		try {
			int value = Integer.parseInt(request.getParameter("value"));
			field.move(value);
		} catch (Exception e) {
		}

		String command = request.getParameter("command");
		if (command != null) {
			switch (command) {
			case "up":
				field.moveUp();
				break;
			case "down":
				field.moveDown();
				break;
			case "left":
				field.moveLeft();
				break;
			case "right":
				field.moveRight();
				break;
			}
		}

		if (field.isSolved()) {
			startPlayingTime = (long) session.getAttribute("playTime");
			long duringTime = System.currentTimeMillis() - startPlayingTime;
			int time = (int) duringTime / 1000;
			out.println("<center><h2>VYHRAL SI</h2>");
			out.println("<h3>Your score is: " + (10000 / time) + "</h3></center>");
			addScore((time), request);
			session.removeAttribute("playTime");
			field = new Field(3, 3);
			session.setAttribute("fild", field);

		}

		out.println("<div class='text-center'>");
		out.println("<table border='1' class='center'>");
		int imgIndx = 0;
		for (int row = 0; row < field.getRowCount(); row++) {
			out.println("<tr>");
			for (int column = 0; column < field.getColumnCount(); column++) {
				out.println("<td>");
				int value = field.getValueAt(row, column);
				if (value == Field.EMPTY_CELL) {
					out.printf(" ");
				} else {
					out.printf("<a href='?action=play&name=NPuzzle&value=%d'><img alt='g' src='images/g3x3/"
							+ (value - 1) + ".jpeg' style='width:100px 'height=100px' ></a>", value);
					imgIndx++;
				}
			}
		}

		out.println("</table>");
		out.println("</div>");

		out.println("<div class='container'>");
		out.println("<div class='text-center'>");
		out.println("<div class='btn-group'>");
		out.println("<form method='get' >");
		out.println("<input type='hidden' name='action' value='play'>");
		out.println("<input type='hidden' name='name' value='NPuzzle'>");

		out.println("<button name='subject' type='submit' value='restart' class='btn btn-warning'>Restart</button>");
		out.println("</form>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");

		// out.println("<form method='get'>");

		// out.println("<input type='hidden' name='action' value='play'>");
		// out.println("<input type='hidden' name='name' value='NPuzzle'>");
		// out.println("Value:<input type='text' name='value'><br>");
		// out.println("<input type='submit'><br>");
		// out.println("</form>");

		// out.println("<div class='text-center'>");
		// out.println("<div class='row'>");
		// out.println("<div class='col-md-5'>");
		// out.println("</div>");
		//
		// out.println("<div class='col-md-2'>");
		// out.println("<a href='?action=play&name=NPuzzle&command=up'><img
		// alt='up' src='images/up.png' style='width:50%'></a>");
		// out.println("</div>");
		// out.println("</div>");
		// out.println("<div class='row'>");
		// out.println("<div class='col-md-4'>");
		// out.println("</div>");
		// out.println("<div class='col-md-2'>");
		// out.println("<a href='?action=play&name=NPuzzle&command=left'><img
		// alt='left' src='images/left.png' style='width:50%'></a>");
		// out.println("</div>");
		//// out.println("<div class='col-md-1'>");
		//// out.println("</div>");
		// out.println("<div class='col-md-2'>");
		// out.println("<a href='?action=play&name=NPuzzle&command=right'><img
		// alt='right' src='images/right.png' style='width:50%'></a>");
		// out.println("</div>");
		// out.println("</div>");
		// out.println("<div class='row'>");
		//
		// out.println("<div class='col-md-5'>");
		// out.println("</div>");
		// out.println("<div class='col-md-2'>");
		//
		// out.println("<a href='?action=play&name=NPuzzle&command=down'><img
		// alt='down' src='images/down.png' style='width:50%'></a>");
		// out.println("</div>");
		// out.println("</div>");
		// out.println("</div>");

		out.println("<br>");
		out.println("<div class='text-center'>");
		out.println("<table class='center responsive'>");
		out.println("<tr>");
		out.println("<td>");
		out.println("<td>");
		out.println(
				"<a href='?action=play&name=NPuzzle&command=up'><img alt='up' src='images/up.png' style='width:80px' 'height=80px'></a>");
		out.println("<td>");
		out.println("<tr>");
		out.println("<td>");
		out.println(
				"<a href='?action=play&name=NPuzzle&command=left'><img alt='left' src='images/left.png' style='width:80px' 'height=80px'></a>");
		out.println("<td>");
		out.println("<td>");
		out.println(
				"<a href='?action=play&name=NPuzzle&command=right'><img alt='right' src='images/right.png' style='width:80px' 'height=80px'></a>");
		out.println("<tr>");
		out.println("<td>");
		out.println("<td>");
		out.println(
				"<a href='?action=play&name=NPuzzle&command=down'><img alt='down' src='images/down.png' style='width:80px' 'height=80px'></a>");
		out.println("<td>");
		out.println("</table>");
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

	private void addScore(int time, HttpServletRequest req) {
		if (req.getSession().getAttribute("user") != null) {

			ScoreServiceHibernateImpl scoreImpl = new ScoreServiceHibernateImpl();
			Score scoreEntity = new Score();
			try {
				scoreEntity.setDate(new Date());
				scoreEntity.setGame(new GameServiceHibernateImpl().getGameByName("NPuzzle"));
				scoreEntity.setPlayer(new PlayerServiceHibernateImpl()
						.getPlayerFromDB((String) req.getSession().getAttribute("user")));
				scoreEntity.setScore(100000 / time);
				scoreImpl.add(scoreEntity);
			} catch (GameException e1) {
				e1.printStackTrace();
			} catch (ScoreException e) {

				e.printStackTrace();
			}

		}
	}
}
