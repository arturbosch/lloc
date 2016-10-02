package io.gitlab.arturbosch.loc.languages;

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
	private final static String GROOVY = "groovy";
	private final static String SCALA = "scala";

	public static final String[] languages = {JAVA, CPP, KOTLIN, GROOVY, SCALA};

	/**
	 * Builds a language strategy for provided language.
	 *
	 * @param language language as string
	 * @return a language strategy for given language
	 */
	public static LanguageStrategy getInstance(final String language) {
		String lang = language.toLowerCase();
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
			if (lang.equals(GROOVY)) {
				return new GroovyStrategy(lang);
			}
			if (lang.equals(SCALA)) {
				return new ScalaStrategy(lang);
			}
		}
		return new NullStrategy("null");
	}
}
