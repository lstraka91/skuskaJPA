package sk.tsystems.gamestudio.services.hibernate;

import java.util.List;

import javax.persistence.EntityManager;

import sk.ness.jpa.JpaHelper;
import sk.tsystems.gamestudio.entity.Player;
import sk.tsystems.gamestudio.exceptions.PlayerException;
import sk.tsystems.gamestudio.services.PlayerService;

public class PlayerServiceHibernateImpl implements PlayerService {
	@Override
	public void addNewPlayer(Player player) throws PlayerException{
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		em.persist(player);
		JpaHelper.commitTransaction();

	}

	@Override
	public Player getPlayerFromDB(String name)throws PlayerException {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		Player player = (Player) em.createQuery("Select p from Player p where p.name=:name").setParameter("name", name)
				.getSingleResult();
		JpaHelper.commitTransaction();

		return player;
	}

	@Override
	public List<Player> getPlayerList() throws PlayerException {
		// TODO Auto-generated method stub
		return null;
	}
}
