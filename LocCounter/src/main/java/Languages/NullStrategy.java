package Languages;

import java.util.List;

/**
 * Created by artur on 10.05.15.
 */
public class NullStrategy extends LanguageStrategy {

	protected NullStrategy(String ending) {
		super(ending);
	}

	@Override
	public int analyze(List<String> lines, boolean isCommentMode, boolean isFullMode) {
		return 0;
	}
}
