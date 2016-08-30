package sk.tsystems;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sk.tsystems.gamestudio.entity.Player;
import sk.tsystems.gamestudio.services.hibernate.PlayerServiceHibernateImpl;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String userName = request.getParameter("userName");
		 String email = request.getParameter("Email");
		 String pass = request.getParameter("Password");
		 Player player = new Player();
		 player.setEmail(email);
		 player.setName(userName);
		 player.setPassword(pass);
		 player.setDate_register(new Date());
		 PlayerServiceHibernateImpl playerService = new PlayerServiceHibernateImpl();
		 try {
			playerService.addNewPlayer(player);
			request.getSession().setAttribute("user", player.getName());
			response.sendRedirect("games");
			return;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		 response.sendRedirect("register?error='invalid'");
		 
		 
	}

}
