package de.artismarti.loc.languages;

import java.util.Arrays;

/**
 * Provides methods to build language strategies.
 * <p>
 * Created by artur on 10.05.15.
 */
public class LanguageStrategyFactory {

	private final static String JAVA = "java";
	private final static String CPP = "cpp";
	private final static String KOTLIN = "kt";

	public static final String[] languages = {JAVA, CPP, KOTLIN};

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
			if (lang.equals(KOTLIN)) {
				return new KotlinStrategy(lang);
			}
		}

		return new NullStrategy("null");
	}
}
