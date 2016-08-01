package sk.tsystems.gamestudio.services.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import sk.tsystems.gamestudio.entity.Game;
import sk.tsystems.gamestudio.entity.Player;
import sk.tsystems.gamestudio.exceptions.GameException;
import sk.tsystems.gamestudio.exceptions.PlayerException;
import sk.tsystems.gamestudio.services.PlayerService;

public class PlayerServiceJDBCImpl implements PlayerService {
private final String SELECT_PLAYER_BY_NAME = "Select name, id_user from player where name=? ";
	@Override
	public void addNewPlayer(Player player) throws PlayerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Player> getPlayerList() throws PlayerException {
		
		return null;
	}

	@Override
	public Player getPlayerFromDB(String name) throws PlayerException {
		Player player = new Player();
		try (Connection connection = DriverManager.getConnection(DatabaseSetting.URL, DatabaseSetting.USER,
				DatabaseSetting.PASSWORD); 
				PreparedStatement ps = connection.prepareStatement(SELECT_PLAYER_BY_NAME)) {
			ps.setString(1, name);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					//player = new Hrac(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
					player.setId(rs.getInt(2));
					player.setName(rs.getString(1));
				}
			}
		} catch (SQLException e) {
			throw new PlayerException("Error during load the player "+name, e);
		}
		return player;
	}

}
