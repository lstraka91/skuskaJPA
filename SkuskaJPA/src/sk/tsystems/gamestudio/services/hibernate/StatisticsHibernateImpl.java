package sk.tsystems.gamestudio.services.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import sk.ness.jpa.JpaHelper;
import sk.tsystems.gamestudio.dTO.GameCommentsCount;
import sk.tsystems.gamestudio.dTO.GameCountScores;
import sk.tsystems.gamestudio.dTO.PlayerCountScore;
import sk.tsystems.gamestudio.dTO.RatingCounts;
import sk.tsystems.gamestudio.entity.Game;
import sk.tsystems.gamestudio.exceptions.GameException;

public class StatisticsHibernateImpl {

	public List<GameCountScores> gameCountScore() {
		List<GameCountScores> gameCountScores = JpaHelper.getEntityManager()
				.createQuery(
						"SELECT NEW sk.tsystems.gamestudio.dTO.GameCountScores(s.game.name, count(s.game.name)) FROM Score s "
								+ "GROUP BY s.game.name ORDER BY count(s.game.name) DESC",
						GameCountScores.class)
				.getResultList();
		return gameCountScores;
	}

	public List<PlayerCountScore> getEachPlayerCountScore() {
		List<PlayerCountScore> playerCountScores = JpaHelper.getEntityManager()
				.createQuery(
						"Select new sk.tsystems.gamestudio.dTO.PlayerCountScore(s.player.name, count(s.player.name))"
								+ "from Score s group by s.player.name order by count(s.player.name) DESC",
						PlayerCountScore.class)
				.getResultList();
		return playerCountScores;
	}

	public long countOfPlayers() {
		return (long) JpaHelper.getEntityManager().createQuery("SELECT count(*) FROM Player p ").getSingleResult();
	}

	public long countOfGames() {
		return (long) JpaHelper.getEntityManager().createQuery("SELECT count(*) FROM Game g ").getSingleResult();
	}

	public long countOfTotalPlayedGames() {
		return (long) JpaHelper.getEntityManager().createQuery("SELECT count(*) FROM Score s ").getSingleResult();
	}

	public GameCountScores getFavoriteGame() {
		if (gameCountScore().size() > 0) {
			return gameCountScore().get(0);
		} else {
			return null;
		}
	}

	public PlayerCountScore getPlayerGambler() {
		if (getEachPlayerCountScore().size() > 0) {
			return getEachPlayerCountScore().get(0);
		} else {
			return null;
		}
	}

	public List<GameCommentsCount> commentsCount() {
		List<GameCommentsCount> gameCountComments = JpaHelper.getEntityManager()
				.createQuery(
						"SELECT NEW sk.tsystems.gamestudio.dTO.GameCommentsCount(c.game.name, count(c.game.name)) FROM Comment c "
								+ "GROUP BY c.game.name ORDER BY count(c.game.name) DESC",
						GameCommentsCount.class)
				.getResultList();
		return gameCountComments;
	}

	public GameCommentsCount getMostCommentedGame() {
		if (commentsCount().size() > 0) {
			return commentsCount().get(0);
		} else {
			return null;
		}
	}

	public List<RatingCounts> getCountRatings() {
		List<RatingCounts> ratingCounts = JpaHelper.getEntityManager()
				.createQuery(
						"SELECT NEW sk.tsystems.gamestudio.dTO.RatingCounts(r.ratingId.game, count(r.ratingId.game)) FROM Rating r "
								+ "GROUP BY r.ratingId.game ORDER BY count(r.ratingId.game) DESC",
						RatingCounts.class)
				.getResultList();

		for (int i = 0; i < ratingCounts.size(); i++) {
			int idGame = ratingCounts.get(i).getIdentGame();
			Game g = (Game) JpaHelper.getEntityManager().createQuery("Select g from Game g where g.identGame=:ident")
					.setParameter("ident", idGame).getSingleResult();
			ratingCounts.get(i).setGameName(g.getGameName());
		}

		return ratingCounts;

	}

}
