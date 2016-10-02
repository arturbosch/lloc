package io.gitlab.arturbosch.loc.exceptions;

import io.gitlab.arturbosch.loc.languages.LanguageStrategyFactory;

/**
 * If language is not supported in {@link LanguageStrategyFactory}.
 * <p>
 * Created by artur on 10.05.15.
 */
public class UnsupportedLanguageException extends Exception {

	public UnsupportedLanguageException(String msg) {
		super(msg);
	}
}
