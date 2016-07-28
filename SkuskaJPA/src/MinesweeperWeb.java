
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
import sk.tsystems.mines.minesweeper.core.Clue;
import sk.tsystems.mines.minesweeper.core.Field;
import sk.tsystems.mines.minesweeper.core.GameState;
import sk.tsystems.mines.minesweeper.core.Mine;
import sk.tsystems.mines.minesweeper.core.Tile;
import sk.tsystems.mines.minesweeper.core.Tile.State;

/**
 * Servlet implementation class MinesweeperWeb
 */
@WebServlet("/Minesweeper")
public class MinesweeperWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Tile tile;
	private long startPlayingTime;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.print("<input type='hidden' name='name' value='Minesweeper'>");
		HttpSession session = request.getSession();

		// session.removeAttribute("field");

		if (session.getAttribute("playingTimeMines") == null) {

			startPlayingTime = System.currentTimeMillis();
			session.setAttribute("playingTimeMines", startPlayingTime);
		}

		Field field = (Field) session.getAttribute("field");
		if (field == null) {
			field = new Field(9, 9, 10);
			session.setAttribute("field", field);
		}

		String toDo = (String) session.getAttribute("subject");
		if (toDo == null) {
			session.setAttribute("subject", "open");
			toDo = (String) session.getAttribute("subject");
		}

		try {
			int row = Integer.parseInt(request.getParameter("row"));
			int column = Integer.parseInt(request.getParameter("column"));
			toDo = (String) session.getAttribute("subject");

			switch (toDo) {

			case "open":

				field.openTile(row, column);
				break;
			case "mark":

				field.markTile(row, column);
				break;
			}
			field.openTile(row, column);

		} catch (Exception e) {
		}
		if (request.getParameter("subject") != null) {

			if (request.getParameter("subject").equals("mark")) {
				session.setAttribute("subject", "mark");
				
			} else if (request.getParameter("subject").equals("open")) {
				session.setAttribute("subject", "open");
				
			} else if (request.getParameter("subject").equals("restart")) {
				field = new Field(9, 9, 10);
				session.setAttribute("field", field);
				session.removeAttribute("playingTimeMines");
			}
		}

		if (field.getState().equals(GameState.FAILED)) {
			out.println("<h2>PREHRAL SI</h2>");

			session.removeAttribute("playingTimeMines");
			field = new Field(9, 9, 10);
			session.setAttribute("field", field);
			session.setAttribute("subject", "open");
		} else if (field.getState().equals(GameState.SOLVED)) {
			startPlayingTime = (long) session.getAttribute("playingTimeMines");
			long duringTime = System.currentTimeMillis() - startPlayingTime;
			int time = (int) duringTime/1000;
			out.println("<center><h2>VYHRAL SI</h2>");
			out.println("<h3>Your score is: "+(10000/time)+"</h3></center>");
			
			addScore((time), request);

			session.removeAttribute("playingTimeMines");
			field = new Field(9, 9, 10);
			session.setAttribute("field", field);
			session.setAttribute("subject", "open");
		}

		renderField(out, field);

		out.println("<div class='container'>");
		out.println("<div class='text-center'>");
		out.println("<div class='btn-group'>");
		out.println("<form method='get' >");
		out.println("<input type='hidden' name='action' value='play'>");
		out.println("<input type='hidden' name='name' value='Minesweeper'>");
		if(session.getAttribute("subject").equals("mark")){
			out.println("<button name='subject' type='submit' value='mark' class='btn btn-primary disabled'>Mark</button>");
		}else{
			
			out.println("<button name='subject' type='submit' value='mark' class='btn btn-primary'>Mark</button>");
		}
		if(session.getAttribute("subject").equals("open")){
		out.println("<button name='subject' type='submit' value='open' class='btn btn-primary' disabled >Open</button>");
		}else{
			out.println("<button name='subject' type='submit' value='open' class='btn btn-primary'>Open</button>");
		}
		out.println("<button name='subject' type='submit' value='restart' class='btn btn-warning'>Restart</button>");
		out.println("</form>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		request.setAttribute("name", "Minesweeper");
		request.setAttribute("action", "play");

	}

	private void renderField(PrintWriter out, Field field) {
		out.println("<div class='text-center'>");
		out.println("<h3> Remaining mines: "+field.getRemainingMineCount()+"</h3> ");
		out.println("<table border='1' class='center'>");
		for (int row = 0; row < field.getRowCount(); row++) {
			out.println("<tr>");
			for (int column = 0; column < field.getColumnCount(); column++) {
				out.println("<td>");
				tile = field.getTile(row, column);
				if (tile.getState() == State.OPEN) {
					if (tile instanceof Mine) {
						out.print("  X");
					} else if (tile instanceof Clue) {
						out.print("<img alt='clue' src='images/" + (((Clue) tile).getValue())
								+ ".png' style='width:35px'>  ");
					}
				} else if (tile.getState() == State.CLOSED) {

					out.printf(
							"<a href='?action=play&name=Minesweeper&row=%d&column=%d'><img alt='backgrnd' src='images/backgroundMine.png' style='width:35px'></a> ",
							row, column);
				} else if (tile.getState() == State.MARKED) {
					out.printf(
							"<a href='?action=play&name=Minesweeper&row=%d&column=%d'><img alt='mark' src='images/mark.png' style='width:35px'></a>",
							row, column);
				}

			}
		}
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
