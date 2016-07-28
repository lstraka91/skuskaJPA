package sk.tsystems.gamestudio.dTO;

public class RatingCounts {
	private String gameName;
	private int identGame;
	private long countRatings;

	
	
	public RatingCounts(int identGame, long countRatings) {
		this.identGame = identGame;
		this.countRatings = countRatings;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public long getCountRatings() {
		return countRatings;
	}

	public void setCountRatings(long countRatings) {
		this.countRatings = countRatings;
	}

	public int getIdentGame() {
		return identGame;
	}

	public void setIdentGame(int identGame) {
		this.identGame = identGame;
	}

}
