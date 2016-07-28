package sk.tsystems.gamestudio.services.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import sk.ness.jpa.JpaHelper;
import sk.tsystems.gamestudio.entity.Game;

public class StatisticsHibernateImpl {

	public Game getFavoriteGame() {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		JpaHelper.commitTransaction();
		ArrayList<Long> favoriteList = (ArrayList<Long>) em
				.createQuery(
						"Select count(*) as count from Score s join s.game g group by g.name order by count(s) desc")
				.getResultList();
		System.out.println(favoriteList.get(0));
		return null;
	}
}
