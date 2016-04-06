package de.artismarti.loc.exceptions;

/**
 * If language is not supported in {@link de.artismarti.loc.languages.LanguageStrategyFactory}.
 * <p>
 * Created by artur on 10.05.15.
 */
public class UnsupportedLanguageException extends Exception {

	public UnsupportedLanguageException(String msg) {
		super(msg);
	}
}
