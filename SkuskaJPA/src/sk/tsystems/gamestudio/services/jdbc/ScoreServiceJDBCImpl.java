package sk.tsystems.gamestudio.services.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.exceptions.ScoreException;
import sk.tsystems.gamestudio.services.ScoreService;

public class ScoreServiceJDBCImpl implements ScoreService {
	private String game;
	private final String SELECT_SCORE = "select s.SCORE,s.DATE_PLAYED,g.name, p.NAME from score s join game g on s.game_ID_GAME = g.ID_GAME join player p on s.player_ID_USER = p.ID_USER WHERE g.name like ? AND rownum <= 10  ORDER BY s.score DESC ";
	private final String INSERT_INTO_SCORE = "insert into SCORE (PLAYER_ID_USER, GAME_ID_GAME, score, date_played, id)	values(?, ?, ?, ?, ? )";
	private int id;

	public ScoreServiceJDBCImpl() {
		game = null;
		Date d = new Date();
		id = d.getDate() + d.getYear() + d.getMonth();
	}

	@Override
	public void add(Score score) throws ScoreException {
		try (Connection connection = DriverManager.getConnection(
				DatabaseSetting.URL, DatabaseSetting.USER,
				DatabaseSetting.PASSWORD);
				PreparedStatement ps = connection
						.prepareStatement(INSERT_INTO_SCORE)) {
			ps.setInt(1, score.getPlayer().getId());
			ps.setInt(2, score.getGame().getIdentGame());
			ps.setInt(3, score.getScore());
			ps.setDate(4, new java.sql.Date(score.getDate().getTime()));
			ps.setInt(5, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new ScoreException("Error saving score", e);
		}

	}

	@Override
	public List<Score> getScoreforGame(String game) throws ScoreException {
		List<Score> scoreList = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(
				DatabaseSetting.URL, DatabaseSetting.USER,
				DatabaseSetting.PASSWORD);
				PreparedStatement ps = connection
						.prepareStatement(SELECT_SCORE)) {
			ps.setString(1, game);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Score score = new Score();
					score.setScore(rs.getInt(1));
					score.setDate(rs.getDate(2));
					score.setGameName(rs.getString(3));
					score.setPlayerName(rs.getString(4));
					scoreList.add(score);
				}
			}
		} catch (SQLException e) {
			throw new ScoreException("Error loading score", e);
		}

		return scoreList;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		int index = 0;
		System.out.println("========TABLE HIGH SCORE========");
		try {
			System.out
					.printf("   %-10s %3s  %s ", "PLAYER", "SCORE", "DATE \n");
			System.out.println("---------------------------------------");
			for (Score score : getScoreforGame(getGame())) {
				index++;
				sb.append(index + ". " + score.toString());

			}
			if (index == 0) {
				sb.append("There is no High Score yet!");
			}
		} catch (ScoreException e) {

			e.printStackTrace();
		}
		sb.append("\n===========================================\n");
		return sb.toString();
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

}
