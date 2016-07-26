package sk.tsystems;

import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sk.ness.jpa.JpaHelper;
import sk.tsystems.gamestudio.entity.Player;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String userName = request.getParameter("userName");
	      //  String passwd = request.getParameter("pass");
	        JpaHelper.beginTransaction();
			 EntityManager em = JpaHelper.getEntityManager();
			 JpaHelper.commitTransaction();
			 ArrayList<Player>playerList= (ArrayList<Player>) em.createQuery("Select p from Player p where p.name=:name").setParameter("name", userName).getResultList();
			 
			 if(playerList.isEmpty()){
				 
				 response.sendRedirect("/GameCenter/gamecenter");
			 }else if (playerList.size()==1){
				 request.getSession().setAttribute("user", playerList.get(0).getName());
				 response.sendRedirect("/GameCenter/games");
								
			 }
	}

}
