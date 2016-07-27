package sk.tsystems.gamestudio.entity;

import java.text.SimpleDateFormat;
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
@Table(name = "Comments")
public class Comment {

	@Id
	@GeneratedValue
	private int ident;

	@Column(name = "comments")
	private String userComment;
	@Transient
	private String gameName;
	@Transient
	private String playerName;

	@ManyToOne(cascade = CascadeType.ALL)
	private Game game;
	@ManyToOne(cascade = CascadeType.ALL)
	private Player player;

	@Column(name = "date_comment")
	private Date dateCommented;

	public Comment(String userComment, Game game, Player player) {

		this.userComment = userComment;
		this.game = game;
		this.player = player;
	}

	public Comment() {

	}

	public int getIdent() {
		return ident;
	}

	public void setIdent(int ident) {
		this.ident = ident;
	}

	public String getUserComment() {
		return userComment;
	}

	public void setUserComment(String userComment) {
		this.userComment = userComment;
	}

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

	public Date getDateCommented() {
		return dateCommented;
	}

	public void setDateCommented(Date dateCommented) {
		this.dateCommented = dateCommented;
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

	public String toString() {
		StringBuilder sb = new StringBuilder();
		Formatter formatter = new Formatter();

		sb.append(formatter.format("%td.%tm.%ty %-10s %s ", getDateCommented(), getDateCommented(), getDateCommented(),
				getPlayerName(), getUserComment()));
		sb.append("\n");
		formatter.close();
		return sb.toString();
	}

	public String getFormattedDate(Date date) {
		String pattern = "MM/dd/yyyy";
		String formatDate;
		SimpleDateFormat simpleFormat = new SimpleDateFormat("dd-MM-yy/HH:mm");
		formatDate = simpleFormat.format(date);
	
		return formatDate;
	}

}
