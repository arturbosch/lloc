package Languages;

import java.util.List;

/**
 * Created by artur on 10.05.15.
 */
public abstract class LanguageStrategy {

	private String ending;

	LanguageStrategy(final String ending) {
		this.ending = ending;
	}

	public String getEnding() {
		return ending;
	}

	public boolean isLangOfFileSame(String filename) {
		return filename.endsWith(ending);
	}

	public abstract int analyze(List<String> lines, boolean isCommentMode, boolean isFullMode);
}
