package sk.tsystems.gamestudio.services;

import java.util.List;

import sk.tsystems.gamestudio.entity.Game;
import sk.tsystems.gamestudio.exceptions.GameException;

public interface GameService {
	public void add(Game game) throws GameException;

	public List<Game> getGameList() throws GameException;
	
	public Game getGameByName(String name) throws GameException;
}
