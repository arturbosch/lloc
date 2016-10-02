package io.gitlab.arturbosch.loc.languages;

import java.util.List;

/**
 * @author artur
 */
class OtherJVMLanguageStrategy extends JavaStrategy {

	OtherJVMLanguageStrategy(String ending) {
		super(ending);
	}

	@Override
	public int analyze(List<String> lines, boolean isCommentMode, boolean isFullMode) {
		int counter = 0;
		boolean escape;

		for (String line : lines) {
			String trimmed = line.trim();
			if (trimmed.length() == 0 || trimmed.length() == 1) {
				continue;
			}

			escape = isEscaped(trimmed, comments);
			if (isCommentMode && escape) {
				counter++;
			}

			if (escape) {
				continue;
			}

			escape = isEscaped(trimmed, escapes);
			if (escape && isFullMode) {
				counter++;
			}

			if (escape) {
				continue;
			}

			if (trimmed.contains(";")) {
				String[] split = trimmed.split(";");
				if (split.length >= 2) {
					counter += split.length;
				}
			}

			counter++;
		}

		return counter;
	}

}
