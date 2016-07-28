package sk.ness.jpa;

import java.util.ArrayList;

import sk.tsystems.gamestudio.dTO.GameCommentsCount;
import sk.tsystems.gamestudio.dTO.GameCountScores;
import sk.tsystems.gamestudio.dTO.PlayerCountScore;
import sk.tsystems.gamestudio.dTO.RatingCounts;
import sk.tsystems.gamestudio.entity.Game;
import sk.tsystems.gamestudio.services.hibernate.StatisticsHibernateImpl;

public class TestStatistics {

	public static void main(String[] args) {
		StatisticsHibernateImpl stats = new StatisticsHibernateImpl();
		ArrayList<GameCountScores> gameC = (ArrayList<GameCountScores>) stats.gameCountScore();
	for (GameCountScores gameCountScores : gameC) {
		
		System.out.println(gameCountScores.getGame() + gameCountScores.getCountScores());
	}
	ArrayList<PlayerCountScore> playSc = (ArrayList<PlayerCountScore>) stats.getEachPlayerCountScore();
	for (PlayerCountScore playerCountScores : playSc) {
		
		System.out.println(playerCountScores.getPlayer() + playerCountScores.getCountScore());
	}
	
	
	ArrayList<GameCommentsCount> gcomCount = (ArrayList<GameCommentsCount>) stats.commentsCount();
	for (GameCommentsCount gc : gcomCount) {
		
		System.out.println(gc.getGameName() + gc.getCountComments());
	}
	
	ArrayList<RatingCounts> ratings = (ArrayList<RatingCounts>) stats.getCountRatings();
	for (RatingCounts rcount : ratings) {
		
		System.out.println(rcount.getIdentGame() +""+ rcount.getCountRatings() + " "+ rcount.getGameName() );
	}
	
	
	}
	

}
