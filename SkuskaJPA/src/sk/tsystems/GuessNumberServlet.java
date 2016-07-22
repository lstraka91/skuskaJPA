package sk.tsystems;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sk.tsystems.guessNumber.GuessNumber;



/**
 * Servlet implementation class GuessNumber
 */
@WebServlet("/guess")
public class GuessNumberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		GuessNumber guesNum= (GuessNumber) session.getAttribute("numbr");
		if (guesNum == null) {
			guesNum = new GuessNumber(20);
			session.setAttribute("numbr", guesNum);
		}

		try {
			int value = Integer.parseInt(request.getParameter("value"));
			
			if(value>guesNum.getToGuess()){
				out.print("Try lower number");
			}else if(value<guesNum.getToGuess()){
				out.print("Try bigger number");
			}else if(value==guesNum.getToGuess()){
				guesNum.setGuessed(true);
			}
		} catch (Exception e) {
		}

		

		if (guesNum.isGuessed()) {
			out.println("<h1>Vyhral si</h1>");
			guesNum = new GuessNumber(20);
			session.setAttribute("numbr", guesNum);
		}

		
		out.println("<form method='get'>");
		out.println("Input number you think I am thinkig on:<input type='text' name='value' autofocus><br>");
		out.println("<input type='submit'><br>");
		out.println("</form>");

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
