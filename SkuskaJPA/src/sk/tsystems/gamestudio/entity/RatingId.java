package sk.tsystems.gamestudio.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RatingId implements Serializable{
	
	@Column(name="id_game")
	private int game;

	@Column(name="id_user")
	private int player;
	
	public int getGameId() {
		return game;
	}
	public void setGameId(int game) {
		this.game = game;
	}
	public int getPlayer() {
		return player;
	}
	public void setPlayer(int player) {
		this.player = player;
	}
	
}

