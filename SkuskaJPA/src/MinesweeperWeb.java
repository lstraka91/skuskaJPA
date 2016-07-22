
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sk.tsystems.mines.minesweeper.core.Clue;
import sk.tsystems.mines.minesweeper.core.Field;
import sk.tsystems.mines.minesweeper.core.GameState;
import sk.tsystems.mines.minesweeper.core.Mine;
import sk.tsystems.mines.minesweeper.core.Tile;
import sk.tsystems.mines.minesweeper.core.Tile.State;

/**
 * Servlet implementation class MinesweeperWeb
 */
@WebServlet("/mines")
public class MinesweeperWeb extends HttpServlet {
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
		Field field = (Field) session.getAttribute("field");
		if (field == null) {
			field = new Field(9, 9, 10);
			session.setAttribute("field", field);
		}

		String toDo = (String) session.getAttribute("subject");
		if (toDo ==null) {
			session.setAttribute("subject", "open");
			toDo=(String) session.getAttribute("subject");
		}
		
		
		
		try {
			int row = Integer.parseInt(request.getParameter("row"));
			int column = Integer.parseInt(request.getParameter("column"));
			toDo = (String) session.getAttribute("subject");
			
			switch (toDo) {
			
			case "open":
				out.println("<h3>Open tiles</h3>");
				field.openTile(row, column);
				break;
			case "mark":
				out.println("<h3>Mark tiles</h3>");
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
			}
		}

		if (field.getState().equals(GameState.FAILED)) {
			out.println("<h1>PREHRAL SI</h1>");
			field = new Field(9, 9, 10);
			session.setAttribute("field", field);
			session.setAttribute("subject", "open");
		} else if (field.getState().equals(GameState.SOLVED)) {
			out.println("<h1>VYHRAL SI</h1>");
			field = new Field(9, 9, 10);
			session.setAttribute("field", field);
			session.setAttribute("subject", "open");
		}

		out.println("<table border='1'>");

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
								+ ".png' style='width:25px'>  ");
					}
				} else if (tile.getState() == State.CLOSED) {

					out.printf(
							"<a href='?row=%d&column=%d'><img alt='backgrnd' src='images/bckgrnd.png' style='width:25px'></a> ",
							row, column);
				} else if (tile.getState() == State.MARKED) {
					out.printf(
							"<a href='?row=%d&column=%d'><img alt='mark' src='images/mark.png' style='width:25px'></a>",
							row, column);
				}
			
			}
		}

		out.println("</table>");
		out.println("<form method='get'>");
		out.println("<button name='subject' type='submit' value='mark'>Mark</button>");
		out.println("<button name='subject' type='submit' value='open'>Open</button>");
		out.println("<button name='subject' type='submit' value='restart'>Restart</button>");
		out.println("</form>");

//		out.println(request.getParameter("row"));
//		out.println(request.getParameter("column"));
//		out.println(request.getParameter("subject"));
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
