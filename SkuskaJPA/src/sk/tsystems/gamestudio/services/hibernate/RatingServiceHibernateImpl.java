package sk.tsystems.gamestudio.services.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import sk.ness.jpa.JpaHelper;
import sk.tsystems.gamestudio.entity.Game;
import sk.tsystems.gamestudio.entity.Rating;
import sk.tsystems.gamestudio.exceptions.GameException;
import sk.tsystems.gamestudio.exceptions.RatingException;
import sk.tsystems.gamestudio.services.RatingService;

public class RatingServiceHibernateImpl implements RatingService {
	private float avgRate;
	private int count;

	public float getAvgRate() {
		return avgRate;
	}

	public void setAvgRate(float avgRate) {
		this.avgRate = avgRate;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public void add(Rating rejting) throws RatingException {
		if (selectRejting(rejting) == 0) {

			JpaHelper.beginTransaction();
			EntityManager em = JpaHelper.getEntityManager();
			em.persist(rejting);
			JpaHelper.commitTransaction();
		} else {
			updateRejting(rejting);
		}
	}

	@Override
	public List<Rating> findRatingForGame(String game) throws RatingException {
		GameServiceHibernateImpl gsimpl = new GameServiceHibernateImpl();
		Game gameFromName=null;
		try {
			gameFromName=gsimpl.getGameByName(game);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		
		JpaHelper.commitTransaction();
		return em
				.createQuery(
						"Select r from Rating r JOIN r.ratingId h where h.game=:gameId")
				.setParameter("gameId", gameFromName.getIdentGame()).getResultList();
	}

	public int selectRejting(Rating rating) {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		JpaHelper.commitTransaction();
		ArrayList<Rating> ratingList = (ArrayList<Rating>) em
				.createQuery("Select r from Rating r where r.ratingId=:rid ")
				.setParameter("rid", rating.getRatingId()).getResultList();
		System.out.println(ratingList);
		System.out.println(ratingList.size());
		return ratingList.size();
	}

	private void updateRejting(Rating rating) {
		EntityManager em = JpaHelper.getEntityManager();
		EntityTransaction updt = em.getTransaction();
		updt.begin();
		em.createQuery("update Rating set rating=:rating where ratingId=:ratingId")
				.setParameter("rating", rating.getRating())
				.setParameter("ratingId", rating.getRatingId()).executeUpdate();
		updt.commit();
	}

	public void getAverageRatingAndCount(Rating rating) {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		List<Long> countList = em
				.createQuery(
						"select COUNT(*) from Rating r join r.ratingId h  where h.game=:gameName group by h.game")
				.setParameter("gameName", rating.getRatingId().getGameId())
				.getResultList();
		JpaHelper.commitTransaction();

		JpaHelper.beginTransaction();
		List<Double> avgList = em
				.createQuery(
						"select round(avg(r.rating),2) from Rating r join r.ratingId h  where h.game=:gameName group by h.game")
				.setParameter("gameName", rating.getRatingId().getGameId())
				.getResultList();
		JpaHelper.commitTransaction();
		double avg = 0;
		long count = 0;
		if (!avgList.isEmpty() || !countList.isEmpty()) {
			avg = avgList.get(0);
			count = countList.get(0);
		}

		setAvgRate((float) avg);
		setCount((int) count);

	}

}
