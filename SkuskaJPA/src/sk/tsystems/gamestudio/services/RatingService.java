package sk.tsystems.gamestudio.services;

import java.util.List;

import sk.tsystems.gamestudio.entity.Rating;
import sk.tsystems.gamestudio.exceptions.RatingException;

public interface RatingService {
	void add(Rating rejting) throws RatingException;
	List<Rating> findRatingForGame(String game) throws RatingException;
}
