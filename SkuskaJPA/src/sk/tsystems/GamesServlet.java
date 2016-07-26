package sk.tsystems;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sk.tsystems.gamestudio.entity.Comment;
import sk.tsystems.gamestudio.entity.Game;
import sk.tsystems.gamestudio.entity.Player;
import sk.tsystems.gamestudio.entity.Rating;
import sk.tsystems.gamestudio.exceptions.GameException;
import sk.tsystems.gamestudio.exceptions.RatingException;
import sk.tsystems.gamestudio.services.hibernate.CommentServiceHibernateImpl;
import sk.tsystems.gamestudio.services.hibernate.GameServiceHibernateImpl;
import sk.tsystems.gamestudio.services.hibernate.PlayerServiceHibernateImpl;
import sk.tsystems.gamestudio.services.hibernate.RatingServiceHibernateImpl;

/**
 * Servlet implementation class GamesServlet
 */
@WebServlet("/games")
public class GamesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<Game> gameList;
	private ArrayList<Comment> commentList;
	private ArrayList<Rating> ratingList;
	private GameServiceHibernateImpl gameservchibrntImpl;
	private RatingServiceHibernateImpl ratingImpl;
	private CommentServiceHibernateImpl commImpl;
	private PlayerServiceHibernateImpl playerImpl;

	@Override
	public void init() throws ServletException {
		super.init();
		gameservchibrntImpl = new GameServiceHibernateImpl();

		try {
			gameList = (ArrayList<Game>) gameservchibrntImpl.getGameList();
		} catch (GameException e) {
			e.printStackTrace();
		}

	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		commImpl = new CommentServiceHibernateImpl();
		playerImpl = new PlayerServiceHibernateImpl();
		ratingImpl = new RatingServiceHibernateImpl();
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {

			if ("play".equals(action) && request.getParameter("name") != null) {

				try {
					ratingList = (ArrayList<Rating>) ratingImpl.findRatingForGame(request.getParameter("name"));
				} catch (RatingException e) {
					e.printStackTrace();
				}

				System.out.println(ratingList.get(0).getPlayerName());
				
				if ("addComment".equals(request.getParameter("addComment")) && !(request.getParameter("comment").trim().isEmpty())) {
					//request.setAttribute(action, "play");
					System.out.println(session.getAttribute("gameName") + " " + session.getAttribute("user"));
					System.out.println(request.getParameter("comment"));
					saveComment(request, session);
				}
				
				commentList = (ArrayList<Comment>) commImpl.findCommentForGame(request.getParameter("name"));
				request.setAttribute("commentList", commentList);
				request.setAttribute("ratingList", ratingList);
//				request.setAttribute("name", request.getParameter("name"));
//				request.setAttribute("play", request.getParameter("action"));
				
				
				session.setAttribute("gameName", request.getParameter("name"));
				request.getRequestDispatcher("/WEB-INF/jsp/gamewindow.jsp").forward(request, response);

			
							// save comment to DB
			} else if ("addComment".equals(request.getParameter("addComment")) && !(request.getParameter("comment").trim().isEmpty())) {
				//request.setAttribute(action, "play");
				System.out.println(session.getAttribute("gameName") + " " + session.getAttribute("user"));
				System.out.println(request.getParameter("comment"));
				saveComment(request, session);

				//commentList = (ArrayList<Comment>) commImpl.findCommentForGame(request.getParameter("name"));
				//request.setAttribute("commentList", commentList);
				//forwardToGameWindow(request, response);

//			} else if ("play".equals(request.getParameter("play"))) {
//				forwardToGameWindow(request, response);
			} else {

				forwardToGameList(request, response);
			}

		} else {
			response.sendRedirect("/GameCenter/gamecenter");
		}
	}

	private void saveComment(HttpServletRequest request, HttpSession session) {
		Comment commentToAdd = new Comment();
		Game playingGame;
		try {
			playingGame = gameservchibrntImpl.getGameByName((String) session.getAttribute("gameName"));
			commentToAdd.setGame(playingGame);
		} catch (GameException e) {
			e.printStackTrace();
		}
		Player loginPlayer = playerImpl.getPlayerFromDB((String) session.getAttribute("user"));
		commentToAdd.setDateCommented(new Date());
		commentToAdd.setPlayer(loginPlayer);
		commentToAdd.setUserComment(request.getParameter("comment"));
		commImpl.add(commentToAdd);
	}

	private void forwardToGameList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("gameList", gameList);
		request.getRequestDispatcher("/WEB-INF/jsp/list_games.jsp").forward(request, response);

	}

	private void forwardToGameWindow(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/gamewindow.jsp").forward(request, response);

	}
}
