package de.artismarti.exceptions;

/**
 * If language is not supported in {@link de.artismarti.languages.LanguageStrategyFactory}.
 * <p>
 * Created by artur on 10.05.15.
 */
public class UnsupportedLanguageException extends Exception {

	public UnsupportedLanguageException(String msg) {
		super(msg);
	}
}
