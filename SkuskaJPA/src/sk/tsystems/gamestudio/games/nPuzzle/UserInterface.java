package sk.tsystems.gamestudio.games.nPuzzle;

public interface UserInterface {

	/**
	 * Starts the game.
	 * 
	 * @param field
	 *            field of mines and clues
	 */
	public abstract void newGame(Field field);

	/**
	 * Updates user interface - prints the field.
	 */
	public abstract void update();
	
	public int getScore();
}
