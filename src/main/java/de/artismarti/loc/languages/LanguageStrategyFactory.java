package de.artismarti.loc.languages;

import java.util.Arrays;

/**
 * Provides methods to build language strategies.
 * <p>
 * Created by artur on 10.05.15.
 */
public class LanguageStrategyFactory {

	public final static String JAVA = "java";
	public final static String CPP = "cpp";

	public static final String[] languages = {JAVA, CPP};

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
			if (lang.equals(CPP)) {
				return new CppStrategy(lang);
			}
		}

		return new NullStrategy("null");
	}
}