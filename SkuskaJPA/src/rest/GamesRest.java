package rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import sk.tsystems.gamestudio.entity.Game;
import sk.tsystems.gamestudio.exceptions.GameException;
import sk.tsystems.gamestudio.services.hibernate.GameServiceHibernateImpl;


@Path("/gamelists")
public class GamesRest {

	GameServiceHibernateImpl gameservice = new GameServiceHibernateImpl();
	public List<Game>games ;
	
	
	@GET
	@Produces("application/json")
	public List<Game> getStudents() {
		try {
			return gameservice.getGameList();
		} catch (GameException e) {
			e.printStackTrace();
		}
		return null;
	}
}
