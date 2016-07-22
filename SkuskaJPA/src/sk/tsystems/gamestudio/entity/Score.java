package sk.tsystems.gamestudio.entity;

import java.util.Date;
import java.util.Formatter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name= "Score")
public class Score {
	@Id
	@GeneratedValue
	private int id;

	private int score;
	@ManyToOne(cascade = CascadeType.ALL)
	private Game game;
	@ManyToOne(cascade = CascadeType.ALL)
	private Player player;
	@Temporal(TemporalType.DATE)
	@Column(name="date_played")
	private Date date;
	@Transient
	private String gameName;
	@Transient
	private String playerName;
	
	
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String toString(){
		StringBuilder sb= new StringBuilder();
		Formatter formatter = new Formatter();
		
		sb.append(formatter.format("%-10s %3d  %td.%tm.%ty ",getPlayerName(),getScore(),getDate(),getDate(),getDate()));
		
//		sb.append(formatter.format("",getPlayerName()));
//		sb.append(formatter.format("%d",getScore()));
		sb.append("\n");
		formatter.close();
		return sb.toString();
	}

}
