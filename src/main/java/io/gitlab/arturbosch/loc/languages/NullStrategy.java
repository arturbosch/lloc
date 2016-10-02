package io.gitlab.arturbosch.loc.languages;

import java.util.List;

/**
 * Null Strategy is used when no language can be matched.
 * <p>
 * Created by artur on 10.05.15.
 */
public class NullStrategy extends LanguageStrategy {

	NullStrategy(String ending) {
		super(ending);
	}

	@Override
	public int analyze(List<String> lines, boolean isCommentMode, boolean isFullMode) {
		return 0;
	}
}
