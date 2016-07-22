package sk.tsystems.gamestudio.services.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sk.tsystems.gamestudio.entity.Comment;
import sk.tsystems.gamestudio.exceptions.CommentException;
import sk.tsystems.gamestudio.services.CommentService;

public class CommentServiceJDBCImpl implements CommentService {
	private String game;
	private final String SELECT_COMMENTS = "select c.comments,g.name, p.NAME,c.DATE_COMMENT from comments c join game g on c.GAME_id_game = g.ID_GAME join player p on c.player_ID_USER = p.ID_USER WHERE g.name like ?";
	private final String INSERT_INTO_COMMENTS = "insert into COMMENTS (PLAYER_ID_USER,GAME_ID_GAME, comments, date_comment, ident) values(?, ?, ?, ?, ? )";
	private int ident;

	public CommentServiceJDBCImpl() {
		Date d = new Date();
		ident = d.getDate() + d.getYear() + d.getMonth();
	}

	@Override
	public void add(Comment comment) throws CommentException {
		try (Connection connection = DriverManager.getConnection(
				DatabaseSetting.URL, DatabaseSetting.USER,
				DatabaseSetting.PASSWORD);
				PreparedStatement ps = connection
						.prepareStatement(INSERT_INTO_COMMENTS)) {
			ps.setInt(1, comment.getPlayer().getId());
			ps.setInt(2, comment.getGame().getIdentGame());
			ps.setString(3, comment.getUserComment());
			ps.setDate(4, new java.sql.Date(comment.getDateCommented()
					.getTime()));
			ps.setInt(5, ident);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new CommentException("Error adding comment to Comments", e);
		}

	}

	@Override
	public List<Comment> findCommentForGame(String game)
			throws CommentException {
		List<Comment> commentList = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(
				DatabaseSetting.URL, DatabaseSetting.USER,
				DatabaseSetting.PASSWORD);
				PreparedStatement ps = connection
						.prepareStatement(SELECT_COMMENTS)) {
			ps.setString(1, game);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Comment comment = new Comment();
					comment.setUserComment(rs.getString(1));
					comment.setGameName(rs.getString(2));
					comment.setPlayerName(rs.getString(3));
					comment.setDateCommented(rs.getDate(4));

					commentList.add(comment);
				}
			}
		} catch (SQLException e) {
			throw new CommentException("Error loading comments", e);
		}

		return commentList;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		int index = 0;
		try {
			System.out.println("===========COMMENTS===========");
			for (Comment comment : findCommentForGame(getGame())) {
				index++;
				sb.append(index + ". " + comment.toString());
			}
			if (index == 0) {
				sb.append("There is no Comments yet!");
			}
		} catch (CommentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sb.toString();
	}

}
