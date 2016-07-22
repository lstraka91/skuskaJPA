package sk.tsystems.gamestudio.services;

import java.util.List;

import sk.tsystems.gamestudio.entity.Comment;
import sk.tsystems.gamestudio.exceptions.CommentException;

public interface CommentService {
	void add(Comment comment) throws CommentException;
	List<Comment> findCommentForGame(String game) throws CommentException;
}
