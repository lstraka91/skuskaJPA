package sk.tsystems.gamestudio.entity;

import java.util.Date;
import java.util.Formatter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "Rating")
public class Rating {

	private int rating;
	@EmbeddedId
	private RatingId ratingId;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_rate")
	private Date rateDate;
	@Transient
	private String playerName;
	@Transient
	private String gameName;

	
	public RatingId getRatingId() {
		return ratingId;
	}

	public void setRatingId(RatingId ratingId) {
		this.ratingId = ratingId;
	}

	public Date getRateDate() {
		return rateDate;
	}

	public void setRateDate(Date rateDate) {
		this.rateDate = rateDate;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}


	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		Formatter formatter = new Formatter();

		sb.append(formatter.format("%-10s %3d  %td.%tm.%ty ", getPlayerName(),
				getRating(), getRateDate(), getRateDate(), getRateDate()));
		sb.append("\n");
		formatter.close();
		return sb.toString();

	}
}
