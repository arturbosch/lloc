package de.artismarti.core;

import de.artismarti.languages.LanguageStrategyFactory;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class LOCTest {

	private static final String JAVA = "java";

	private static final int EXPECTED_LOC_WITHOUT_COMMENTS = 6;
	private static final int EXPECTED_LOC_WITH_COMMENTS = 16;
	private static final int EXPECTED_LOC_WITH_COMMENTS_AND_IMPORTS_ETC = 18;

	private static File file = new File("./src/main/java/de/artismarti/exceptions/UnsupportedLanguageException.java");
	private static File file2 = new File("./src/main/java/de/artismarti/exceptions/ToFewArgumentsException.java");

	@Test
	public void countLOCForTwoFiles() {
		assertThat(EXPECTED_LOC_WITHOUT_COMMENTS,
				is(LOC.count(LanguageStrategyFactory.getInstance(JAVA), file, file2)));
	}

	@Test
	public void countLOCWithCommentsForTwoFiles() {
		assertThat(EXPECTED_LOC_WITH_COMMENTS,
				is(LOC.countWithComments(LanguageStrategyFactory.getInstance(JAVA), file, file2)));
	}

	@Test
	public void countFullModeLOCWithCommentsForTwoFiles() {
		assertThat(EXPECTED_LOC_WITH_COMMENTS_AND_IMPORTS_ETC,
				is(LOC.countWithModes(LanguageStrategyFactory.getInstance(JAVA), true, true, false, file, file2)));
	}

}
