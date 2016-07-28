package sk.ness.jpa;

import java.util.ArrayList;

import sk.tsystems.gamestudio.entity.Game;
import sk.tsystems.gamestudio.services.hibernate.StatisticsHibernateImpl;

public class TestStatistics {

	public static void main(String[] args) {
		StatisticsHibernateImpl stats = new StatisticsHibernateImpl();
		Game count = stats.getFavoriteGame();
		
		
	}

}
