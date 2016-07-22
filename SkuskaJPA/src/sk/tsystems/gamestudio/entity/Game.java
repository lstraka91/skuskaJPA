package sk.tsystems.gamestudio.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Game {

	@Id
	@GeneratedValue
	@Column(name="id_game")
	private int identGame;
	
	private String name;
	private String url;
	private String author;
	@Temporal(TemporalType.DATE)
	@Column(name="date_created")
	private Date dateCreated;

	public Game(int identGame, String gameName, String author, String gameURL) {

		this.identGame = identGame;
		this.name = gameName;
		this.url = gameURL;
		this.author = author;
	}
	
	public Game(){
		
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public int getIdentGame() {
		return identGame;
	}

	public void setIdentGame(int identGame) {
		this.identGame = identGame;
	}

	public String getGameName() {
		return name;
	}

	public void setGameName(String gameName) {
		this.name = gameName;
	}

	public String getGameURL() {
		return url;
	}

	public void setGameURL(String gameURL) {
		this.url = gameURL;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getGameName());
		return sb.toString();
	}
}
