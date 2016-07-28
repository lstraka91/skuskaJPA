package sk.tsystems.gamestudio.entity;

public class Statistics {

	/*
	 * select new GameCountScore(s.game.name, count(s.game.name)) FROM Score s group by (s.game.name) 
	 * order by count (s.game.name) DESC" ,GameCountScores.class
	 */
	
	private String game;
	private String player;
	private long countScores;
	
}
