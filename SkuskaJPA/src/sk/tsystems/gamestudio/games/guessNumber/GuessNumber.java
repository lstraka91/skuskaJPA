package sk.tsystems.gamestudio.games.guessNumber;

import java.util.Random;
import java.util.Scanner;

import sk.tsystems.gamestudio.games.GameInterface;

public class GuessNumber implements GuessNumberInterface, Runnable, GameInterface {
	private int toGuess;
	private int turns;
	private boolean guessed;
	private int range;
	private long startMillis;
	private int score;

	@Override
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getTurns() {
		return turns;
	}

	public boolean isGuessed() {
		return guessed;
	}

	public GuessNumber(int range) {
		toGuess = new Random().nextInt(range);
		turns = 0;
		guessed = false;
		this.range = range;
		startMillis = System.currentTimeMillis();
		score = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sk.tsystems.gamestudio.games.guessNumber.GuessNumberInterface#guess(int)
	 */
	@Override
	public void guess(int number) {
		while (!isGuessed()) {
			turns++;
			System.out.println("Enter number from 0 to " + number);
			Scanner readLine = new Scanner(System.in);
			if (readLine.hasNextInt()) {
				System.out.println("Playing time: " + getPlayingSeconds());
				int guess = readLine.nextInt();
				if (guess == toGuess) {
					guessed = true;
					System.out.println("Congrats! You guess the number on "
							+ getTurns() + " turns");
					setScore(getPlayingSeconds());
				} else if (guess > toGuess) {
					System.out.println("Try again with lower number");
				} else if (guess < toGuess) {
					System.out.println("Try again with bigger number");
				}
			} else if (readLine.hasNext("x|X|exit")) {
				guessed = true;
			}
			// readLine.close();
		}
	}

	/**
	 * Get playing time
	 * 
	 * @return int in seconds of playing
	 */
	public int getPlayingSeconds() {
		int currentTime = (int) ((System.currentTimeMillis() - startMillis) / 1000L);
		return currentTime;
	}

	@Override
	public void run() {
		guess(range);
	}

}