package sk.tsystems.gamestudio.services.hibernate;

import javax.persistence.EntityManager;

import sk.ness.jpa.JpaHelper;
import sk.tsystems.gamestudio.entity.Player;

public class PlayerServiceHibernateImpl {

	public void addNewPlayer(Player player){
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		em.persist(player);
		JpaHelper.commitTransaction();

	}
	
	public Player getPlayerFromDB(String name){
		 JpaHelper.beginTransaction();
		 EntityManager em = JpaHelper.getEntityManager();
		 JpaHelper.commitTransaction();
		 Player hrac = (Player) em.createQuery("Select p from Player p where p.name=:name").setParameter("name", name).getSingleResult();
	
		 return hrac;
	}
}
