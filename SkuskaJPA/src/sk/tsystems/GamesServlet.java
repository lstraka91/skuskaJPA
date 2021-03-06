package sk.tsystems;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sk.ness.jpa.JpaHelper;
import sk.tsystems.gamestudio.entity.Comment;
import sk.tsystems.gamestudio.entity.Game;
import sk.tsystems.gamestudio.entity.Player;
import sk.tsystems.gamestudio.entity.Rating;
import sk.tsystems.gamestudio.entity.RatingId;
import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.exceptions.GameException;
import sk.tsystems.gamestudio.exceptions.PlayerException;
import sk.tsystems.gamestudio.exceptions.RatingException;
import sk.tsystems.gamestudio.exceptions.ScoreException;
import sk.tsystems.gamestudio.services.hibernate.CommentServiceHibernateImpl;
import sk.tsystems.gamestudio.services.hibernate.GameServiceHibernateImpl;
import sk.tsystems.gamestudio.services.hibernate.PlayerServiceHibernateImpl;
import sk.tsystems.gamestudio.services.hibernate.RatingServiceHibernateImpl;
import sk.tsystems.gamestudio.services.hibernate.ScoreServiceHibernateImpl;
import sun.print.resources.serviceui_es;

/**
 * Servlet implementation class GamesServlet
 */
@WebServlet("/games")
public class GamesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<Game> gameList;
	private ArrayList<Comment> commentList;
	private ArrayList<Score> scoreList;
	private ArrayList<Rating> ratingList;
	private GameServiceHibernateImpl gameservchibrntImpl;
	private RatingServiceHibernateImpl ratingImpl;
	private CommentServiceHibernateImpl commImpl;
	private PlayerServiceHibernateImpl playerImpl;
	private ScoreServiceHibernateImpl scoreImpl;

	@Override
	public void init() throws ServletException {
		super.init();
		gameservchibrntImpl = new GameServiceHibernateImpl();
		commImpl = new CommentServiceHibernateImpl();
		playerImpl = new PlayerServiceHibernateImpl();
		ratingImpl = new RatingServiceHibernateImpl();
		scoreImpl = new ScoreServiceHibernateImpl();
		try {
			gameList = (ArrayList<Game>) gameservchibrntImpl.getGameList();
		} catch (GameException e) {
			e.printStackTrace();
		}

	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		session.setAttribute("gameList", gameList);
		if (session.getAttribute("user") != null) {

			if ("play".equals(action) && request.getParameter("name") != null) {
				String gameName = request.getParameter("name");

				if ("addComment".equals(request.getParameter("addComment"))
						&& !(request.getParameter("comment").trim().isEmpty())) {

					saveComment(request, session);
				} else if ("addRating".equals(request.getParameter("addRating"))
						&& !(request.getParameter("rating").trim().isEmpty())) {
					addNewRating(request, session);
				} else if ("delete".equals(request.getParameter("delete"))) {
					int id = Integer.parseInt(request.getParameter("id"));
					request.removeAttribute("comentDelete");
					if(session.getAttribute("user").equals(request.getParameter("comentUser"))){
						
					deleteComment(id);
					
					}else{
						request.setAttribute("comentDelete", "comentDelete");
					}
				}

				getComments(request, gameName);

				request.setAttribute("name", gameName);

				getTopScore(request, gameName);

				getAVGandCountRating(gameName, request);
				session.setAttribute("gameName", gameName);
				request.getRequestDispatcher("/WEB-INF/jsp/gamewindow.jsp").forward(request, response);

			} else {

				forwardToGameList(request, response);
			}

		} else if (session.getAttribute("user") == null) {
			if ("play".equals(action) && request.getParameter("name") != null) {
				String gameName = request.getParameter("name");

				getComments(request, gameName);
				request.setAttribute("name", gameName);
				getTopScore(request, gameName);
				getAVGandCountRating(gameName, request);
				session.setAttribute("gameName", gameName);
				request.getRequestDispatcher("/WEB-INF/jsp/gamewindow.jsp").forward(request, response);

			} else {

				forwardToGameList(request, response);
			}
		}
	}

	private void getTopScore(HttpServletRequest request, String gameName) {
		try {
			scoreList = (ArrayList<Score>) scoreImpl.getScoreforGame(gameName);
			request.setAttribute("scoreList", scoreList);
		} catch (ScoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getComments(HttpServletRequest request, String gameName) {
		commentList = (ArrayList<Comment>) commImpl.findCommentForGame(gameName);
		request.setAttribute("commentList", commentList);
	}

	private void getRatings(HttpServletRequest request, String gameName) {
		try {
			ratingList = (ArrayList<Rating>) ratingImpl.findRatingForGame(gameName);

		} catch (RatingException e) {
			e.printStackTrace();
		}
		request.setAttribute("ratingList", ratingList);
	}

	private void saveComment(HttpServletRequest request, HttpSession session) {
		Comment commentToAdd = new Comment();
		System.out.println(session.getAttribute("gameName"));
		Game playingGame;
		try {
			playingGame = gameservchibrntImpl.getGameByName((String) session.getAttribute("gameName"));
			commentToAdd.setGame(playingGame);
		} catch (GameException e) {
			e.printStackTrace();
		}
		Player loginPlayer;
		try {
			loginPlayer = playerImpl.getPlayerFromDB((String) session.getAttribute("user"));
			commentToAdd.setDateCommented(new Date());
			commentToAdd.setPlayer(loginPlayer);
			commentToAdd.setUserComment(request.getParameter("comment"));
			commImpl.add(commentToAdd);
		} catch (PlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	private void getAVGandCountRating(String gameName, HttpServletRequest req) {
		try {
			Game game = gameservchibrntImpl.getGameByName(gameName);

			Rating rating = new Rating();

			RatingId rId = new RatingId();
			rId.setGameId(game.getIdentGame());
			rating.setRatingId(rId);
			ratingImpl.getAverageRatingAndCount(rating);
			req.setAttribute("average", ratingImpl.getAvgRate());
			req.setAttribute("count", ratingImpl.getCount());

		} catch (GameException e) {

			e.printStackTrace();
		}
	}

	private void addNewRating(HttpServletRequest request, HttpSession session) {
		Rating rating = new Rating();
		try {
			rating.setRateDate(new Date());
			rating.setRating(Integer.parseInt(request.getParameter("rating")));
			RatingId rid = new RatingId();
			rid.setGameId(gameservchibrntImpl.getGameByName(request.getParameter("name")).getIdentGame());
			rid.setPlayer(playerImpl.getPlayerFromDB((String) session.getAttribute("user")).getId());
			rating.setRatingId(rid);
			ratingImpl.add(rating);
		} catch (RatingException e) {
			e.printStackTrace();
		} catch (GameException e) {
			e.printStackTrace();
		} catch (PlayerException e) {
			e.printStackTrace();
		}
	}

	private void deleteComment(int id) {
		EntityManager em = JpaHelper.getEntityManager();
		Comment commentToDelete = em.find(Comment.class, id);
		em.getTransaction().begin();
		em.remove(commentToDelete);
		em.getTransaction().commit();
	}
}
