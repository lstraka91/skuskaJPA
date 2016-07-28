package sk.tsystems.gamestudio.dTO;

public class GameCountScores {

	 
		
			private String game;

			private Long countScores;

			public GameCountScores(String game, Long countScores) {
				this.game = game;
				this.countScores = countScores;
			}

			public String getGame() {
				return game;
			}

			public void setGame(String game) {
				this.game = game;
			}

			public Long getCountScores() {
				return countScores;
			}

			public void setCountScores(Long countScores) {
				this.countScores = countScores;
			}

}
