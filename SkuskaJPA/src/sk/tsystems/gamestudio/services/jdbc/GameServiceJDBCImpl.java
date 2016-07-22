package sk.tsystems.gamestudio.services.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sk.tsystems.gamestudio.entity.Game;
import sk.tsystems.gamestudio.exceptions.GameException;
import sk.tsystems.gamestudio.services.GameService;

public class GameServiceJDBCImpl implements GameService{
	private final String SELECT_GAMES= "SELECT * FROM GAME";
	private final String SELECT_GAME_BY_NAME= "SELECT * FROM GAME WHERE NAME = ?";
	
	@Override
	public void add(Game game)throws GameException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Game> getGameList() throws GameException {
		List<Game> games = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(DatabaseSetting.URL, DatabaseSetting.USER,
				DatabaseSetting.PASSWORD); PreparedStatement ps = connection.prepareStatement(SELECT_GAMES)) {
			
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Game game = new Game(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
					games.add(game);
				}
			}
		} catch (SQLException e) {
			throw new GameException("Error during load the games", e);
		}

		return games;
	}
	@Override
	public Game getGameByName(String name) throws GameException{
		Game game = null;
		try (Connection connection = DriverManager.getConnection(DatabaseSetting.URL, DatabaseSetting.USER,
				DatabaseSetting.PASSWORD); 
				PreparedStatement ps = connection.prepareStatement(SELECT_GAME_BY_NAME)) {
			ps.setString(1, name);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					game = new Game(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
					
				}
			}
		} catch (SQLException e) {
			throw new GameException("Error during load the game "+name, e);
		}

		return game;
	}

	
}
