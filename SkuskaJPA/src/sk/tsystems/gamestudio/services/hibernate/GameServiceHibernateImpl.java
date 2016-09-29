package sk.tsystems.gamestudio.services.hibernate;

import java.util.List;

import javax.persistence.EntityManager;

import sk.ness.jpa.JpaHelper;
import sk.tsystems.gamestudio.entity.Game;
import sk.tsystems.gamestudio.exceptions.GameException;
import sk.tsystems.gamestudio.services.GameService;

public class GameServiceHibernateImpl implements GameService {


	@Override
	public void add(Game game) throws GameException {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		em.persist(game);
		JpaHelper.commitTransaction();

	}

	@Override
	public List<Game> getGameList() throws GameException {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		JpaHelper.commitTransaction();
		List<Game> gamesList = em.createQuery("Select g from Game g")
				.getResultList();
		return gamesList;
	}

	@Override
	public Game getGameByName(String name) throws GameException {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		JpaHelper.commitTransaction();
		List<Game> game =   em
				.createQuery("Select g from Game g where g.name=:gameName")
				.setParameter("gameName", name).getResultList();

		if(game.isEmpty()){
			return null;
		}else{
			
			return game.get(0);
		}
	}
}
