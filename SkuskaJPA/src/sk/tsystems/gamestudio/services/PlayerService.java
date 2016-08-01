package sk.tsystems.gamestudio.services;

import java.util.List;

import sk.tsystems.gamestudio.entity.Player;
import sk.tsystems.gamestudio.exceptions.PlayerException;

public interface PlayerService {
	public void addNewPlayer(Player player) throws PlayerException;

	public List<Player> getPlayerList() throws PlayerException;
	
	public Player getPlayerFromDB(String name) throws PlayerException;
}
