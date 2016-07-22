package sk.tsystems.gamestudio.services.hibernate;

import java.util.List;

import javax.persistence.EntityManager;

import sk.ness.jpa.JpaHelper;
import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.exceptions.ScoreException;
import sk.tsystems.gamestudio.services.ScoreService;

public class ScoreServiceHibernateImpl implements ScoreService{

	@Override
	public void add(Score score) throws ScoreException {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		em.persist(score);
		JpaHelper.commitTransaction();
		
	}

	@Override
	public List<Score> getScoreforGame(String game) throws ScoreException {
		 JpaHelper.beginTransaction();
		 EntityManager em = JpaHelper.getEntityManager();
		 JpaHelper.commitTransaction();
		 return em.createQuery("Select s from Score s JOIN s.game h where h.name=:gameName order by s.score desc").setParameter("gameName", game).setMaxResults(10).getResultList();
	}

}
