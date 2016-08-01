package sk.tsystems;

import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.exceptions.GameException;
import sk.tsystems.gamestudio.exceptions.PlayerException;
import sk.tsystems.gamestudio.exceptions.ScoreException;
import sk.tsystems.gamestudio.services.hibernate.GameServiceHibernateImpl;
import sk.tsystems.gamestudio.services.hibernate.PlayerServiceHibernateImpl;
import sk.tsystems.gamestudio.services.hibernate.ScoreServiceHibernateImpl;

public abstract class GamesServletServices extends HttpServlet{

	
	
	public void addScore(HttpServletRequest request, int score, String gameName){
		if (request.getSession().getAttribute("user") != null) {
			String playerName= (String) request.getSession().getAttribute("user");
			
			ScoreServiceHibernateImpl scoreImpl = new ScoreServiceHibernateImpl();
			Score scoreEntity = new Score();
			try {
				scoreEntity.setDate(new Date());
				scoreEntity.setGame(new GameServiceHibernateImpl().getGameByName(gameName));
				scoreEntity.setPlayer(new PlayerServiceHibernateImpl()
						.getPlayerFromDB(playerName));
				scoreEntity.setScore(score);
				scoreImpl.add(scoreEntity);
			} catch (GameException e1) {
				e1.printStackTrace();
			} catch (ScoreException e) {

				e.printStackTrace();
			} catch (PlayerException e) {
				e.printStackTrace();
			}

		}
	}
	
	
	
		
	
}
