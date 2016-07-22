package sk.tsystems.gamestudio.services;

import java.util.List;

import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.exceptions.ScoreException;

public interface ScoreService {
	void add(Score skore) throws ScoreException;
	List<Score> getScoreforGame(String game) throws ScoreException;
}
