package sk.tsystems.gamestudio.games.nPuzzle;

import java.io.Serializable;

public class Stone extends Tile implements Serializable {
	private final int value;

	public Stone(int value) {
		this.value = value;

	}

	public int getValue() {
		return value;
	}

}
