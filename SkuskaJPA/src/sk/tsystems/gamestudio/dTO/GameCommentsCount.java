package sk.tsystems.gamestudio.dTO;

public class GameCommentsCount {
private String gameName;
private long countComments;

public GameCommentsCount(String gameName, long countComments) {
	this.gameName = gameName;
	this.countComments = countComments;
}

public String getGameName() {
	return gameName;
}

public void setGameName(String gameName) {
	this.gameName = gameName;
}

public long getCountComments() {
	return countComments;
}

public void setCountComments(long countComments) {
	this.countComments = countComments;
}


}
