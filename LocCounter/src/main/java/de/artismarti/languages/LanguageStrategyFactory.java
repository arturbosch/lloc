package de.artismarti.languages;

import java.util.Arrays;

/**
 * Provides methods to build language strategies.
 * <p>
 * Created by artur on 10.05.15.
 */
public class LanguageStrategyFactory {

	public static final String[] languages = {"java"};
	public final static String JAVA = "java";

	/**
	 * Builds a language strategy for provided language.
	 *
	 * @param lang language as string
	 * @return a language strategy for given language
	 */
	public static LanguageStrategy getInstance(final String lang) {
		if (Arrays.asList(languages).contains(lang)) {
			if (lang.equals(JAVA)) {
				return new JavaStrategy(lang);
			}
		}

		return new NullStrategy("null");
	}
}
