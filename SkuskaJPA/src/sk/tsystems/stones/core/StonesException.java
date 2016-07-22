package sk.tsystems.stones.core;

public class StonesException extends RuntimeException {

	public StonesException(String message, Throwable cause) {
		super(message, cause);
	}

	public StonesException(String message) {
		super(message);
	}	
}
