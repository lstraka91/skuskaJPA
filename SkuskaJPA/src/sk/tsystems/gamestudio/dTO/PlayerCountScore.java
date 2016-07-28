package sk.tsystems.gamestudio.dTO;

public class PlayerCountScore {
private String player;
private long countScore;



public String getPlayer() {
	return player;
}
public void setPlayer(String player) {
	this.player = player;
}
public long getCountScore() {
	return countScore;
}
public void setCountScore(long countScore) {
	this.countScore = countScore;
}

public PlayerCountScore(String player, long countScore) {
	this.player = player;
	this.countScore = countScore;
}

}
