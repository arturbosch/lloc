package de.artismarti.exceptions;

/**
 * If there to few arguments for the main method.
 * <p>
 * Created by artur on 10.05.15.
 */
public class ToFewArgumentsException extends Exception {

	public ToFewArgumentsException(String msg) {
		super(msg);
	}
}
