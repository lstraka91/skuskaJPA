
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Student;
import sk.tsystems.gamestudio.entity.Comment;
import sk.tsystems.gamestudio.entity.Game;
import sk.tsystems.gamestudio.entity.Player;
import sk.tsystems.gamestudio.entity.Rating;
import sk.tsystems.gamestudio.exceptions.GameException;
import sk.tsystems.gamestudio.exceptions.RatingException;
import sk.tsystems.gamestudio.services.hibernate.CommentServiceHibernateImpl;
import sk.tsystems.gamestudio.services.hibernate.GameServiceHibernateImpl;
import sk.tsystems.gamestudio.services.hibernate.RatingServiceHibernateImpl;

@WebServlet("/students")
public class StudentsServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<Integer, Student> students;
	private ArrayList<Game> gameList;
	private ArrayList<Comment> commentList;
	private ArrayList<Rating> ratingList;

	@Override
	public void init() throws ServletException {
		super.init();

		GameServiceHibernateImpl gshimpl = new GameServiceHibernateImpl();
		try {
			gameList = (ArrayList<Game>) gshimpl.getGameList();
		} catch (GameException e) {
			e.printStackTrace();
		}
		
//		CommentServiceHibernateImpl commImpl = new CommentServiceHibernateImpl();
//		Comment com = new Comment();
//		Player pl = new Player();
//		pl.setName("Anton");
//		pl.setPassword("456fdas");
//		pl.setEmail("ee");
//		pl.setDate_register(new Date());
//		com.setPlayer(pl);
//		Game g=null;
//		try {
//			g = gshimpl.getGameByName("Minesweeper");
//		} catch (GameException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		com.setGame(g);
//		com.setUserComment("haha");
//		com.setDateCommented(new Date());
//		commImpl.add(com);

	
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		String action = request.getParameter("action");
		if ("play".equals(action) && request.getParameter("name") != null) {
			//request.getRequestDispatcher("/Minesweeper").include(request, response);
			CommentServiceHibernateImpl commImpl = new CommentServiceHibernateImpl();
			commentList=(ArrayList<Comment>) commImpl.findCommentForGame(request.getParameter("name"));
			RatingServiceHibernateImpl ratingImpl = new RatingServiceHibernateImpl();
			try {
				ratingList= (ArrayList<Rating>) ratingImpl.findRatingForGame(request.getParameter("name"));
			} catch (RatingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(ratingList.get(0).getPlayerName());
			request.setAttribute("commentList", commentList);
			request.setAttribute("ratingList", ratingList);
			request.setAttribute("name", request.getParameter("name"));
			request.setAttribute("play", request.getParameter("action"));
			request.getRequestDispatcher("/WEB-INF/jsp/gamewindow.jsp").forward(request, response);
			
//			response.sendRedirect("/GameCenter/"+request.getParameter("name"));
//			request.getRequestDispatcher("/WEB-INF/jsp/edit_student.jsp").forward(request, response);
			// } else if ("add".equals(action)) {
			// request.getRequestDispatcher("/WEB-INF/jsp/add_student.jsp").forward(request,
			// response);
			// } else if ("edit".equals(action) && request.getParameter("id") !=
			// null) {
			// Student student =
			// students.get(Integer.parseInt(request.getParameter("id")));
			// request.setAttribute("student", student);
			// request.getRequestDispatcher("/WEB-INF/jsp/edit_student.jsp").forward(request,
			// response);
			// } else if ("insert".equals(action)) {
			// int id = students.size() + 1;
			// Student student = new Student(id,
			// request.getParameter("firstName"),
			// request.getParameter("lastName"));
			// students.put(id, student);
			// forwardToList(request, response);
			// } else if ("save".equals(action)) {
			// Student student =
			// students.get(Integer.parseInt(request.getParameter("id")));
			// student.setFirstName(request.getParameter("firstName"));
			// student.setLastName(request.getParameter("lastName"));
			// forwardToList(request, response);
		} else if("Minesweeper".equals(request.getAttribute("name"))){
			CommentServiceHibernateImpl commImpl = new CommentServiceHibernateImpl();
			commentList=(ArrayList<Comment>) commImpl.findCommentForGame(request.getParameter("name"));
			RatingServiceHibernateImpl ratingImpl = new RatingServiceHibernateImpl();
			try {
				ratingList= (ArrayList<Rating>) ratingImpl.findRatingForGame(request.getParameter("name"));
			} catch (RatingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(ratingList.get(0).getPlayerName());
			request.setAttribute("commentList", commentList);
			request.setAttribute("ratingList", ratingList);
			request.setAttribute("name", request.getParameter("name"));
			request.setAttribute("play", request.getParameter("action"));
			request.getRequestDispatcher("/WEB-INF/jsp/gamewindow.jsp").forward(request, response);
		}
		else {
		
			forwardToList(request, response);
		}

	}

	private void forwardToList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// request.setAttribute("students", students.values());
		// request.getRequestDispatcher("/WEB-INF/jsp/list_students.jsp").forward(request,
		// response);
		request.setAttribute("gameList", gameList);
		request.getRequestDispatcher("/WEB-INF/jsp/list_games.jsp").forward(request, response);

	}
}
