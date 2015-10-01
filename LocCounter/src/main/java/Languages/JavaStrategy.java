package Languages;

import java.util.List;

/**
 * Created by artur on 10.05.15.
 * Updated by artur on 03.07.15:
 *     - Counts now the logical LOCs by conventions.
 *     - Conventions are that no more than one statement is allowed to be in one line.
 *     - A Statement can grow over one line, but will be counted as one LOC.
 */
public class JavaStrategy extends LanguageStrategy {

	private String[] comments = {"//", "/*", "*/", "*"};
	private String[] escapes = {"import", "package"};

	protected JavaStrategy(String ending) {
		super(ending);
	}

	@Override
	public int analyze(List<String> lines, boolean isCommentMode, boolean isFullMode) {

		int counter = 0, openedBrackets = 0, closedBrackets = 0;
		boolean escape;

		for (String line : lines) {

			String trimmed = line.trim();

			if (trimmed.isEmpty()) {
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
				counter++;
			}

			if (trimmed.contains("{")) {
				openedBrackets++;
			}

			if (trimmed.contains("}")) {
				closedBrackets++;
			}
		}

		int div = openedBrackets - closedBrackets;

		if (div == 0) {
			counter += openedBrackets;
		} else {
			counter = -1;
		}

		return counter;
	}

	private boolean isEscaped(String trimmed, String[] rules) {
		for (String rule : rules) {
			if (trimmed.startsWith(rule)) {
				return true;
			}
		}
		return false;
	}
}
