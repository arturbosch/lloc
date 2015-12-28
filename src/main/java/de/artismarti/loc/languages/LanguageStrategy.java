package de.artismarti.loc.languages;

import java.util.List;

/**
 * Abstract class for language strategies. Subclasses have to override the analyze method.
 * <p/>
 * Created by artur on 10.05.15.
 */
public abstract class LanguageStrategy {

	private String ending;

	LanguageStrategy(final String ending) {
		this.ending = ending;
	}

	/**
	 * @return the ending of this language strategy
	 */
	public String getEnding() {
		return ending;
	}

	/**
	 * Checks if the given filename has the same ending of this language strategy.
	 * This uses the simple ending check also used for instantiating the language strategy
	 * via the factory. If a language has many endings you can override this method.
	 *
	 * @param filename file to check language
	 * @return true if endings are equal
	 */
	public boolean isLangOfFileSame(String filename) {
		return filename.endsWith(ending);
	}

	/**
	 * Analyzes the given lines and returns the logical loc.
	 *
	 * @param lines         lines to analyze
	 * @param isCommentMode if lines of comments should be counted too
	 * @param isFullMode    if import and package statements should be counted too
	 * @return loc of the lines
	 */
	public abstract int analyze(List<String> lines, boolean isCommentMode, boolean isFullMode);
}
