package de.artismarti.core;

import de.artismarti.languages.LanguageStrategyFactory;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class LOCTest {

	private static final String JAVA = "java";

	private static final int EXPECTED_LOC_WITHOUT_COMMENTS = 6;
	private static final int EXPECTED_LOC_WITH_COMMENTS = 16;
	private static final int EXPECTED_LOC_WITH_COMMENTS_AND_IMPORTS_ETC = 18;

	private static File file = new File("./src/main/java/de/artismarti/exceptions/UnsupportedLanguageException.java");
	private static File file2 = new File("./src/main/java/de/artismarti/exceptions/ToFewArgumentsException.java");
	private static File SRC_FILE = new File("./src");

	private static File del_file = new File("./UnsupportedLanguageException.java.txt");
	private static File del_file2 = new File("./ToFewArgumentsException.java.txt");
	private static File del_file_src = new File("./src.txt");

	@After
	public void tearDown() throws IOException {
		if (del_file.exists()) {
			Files.delete(del_file.toPath());
		}
		if (del_file2.exists()) {
			Files.delete(del_file2.toPath());
		}
		if (del_file_src.exists()) {
			Files.delete(del_file_src.toPath());
		}
	}

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
				is(LOC.countWithModes(LanguageStrategyFactory.getInstance(JAVA), true, true, false, false, file, file2)));
	}

	@Test
	public void printSortedLocFileForTwoFiles() throws IOException {
		LOC.countWithModes(LanguageStrategyFactory.getInstance(JAVA),
				true, true, true, true, SRC_FILE, file2);
		List<String> lines = Files.readAllLines(Paths.get("./src.txt"), Charset.forName("ISO_8859_1"));

		assertThat(isSorted(lines), is(true));
	}

	private static <T extends Comparable<? super T>> boolean isSorted(List<T> array){
		for (int i = 0; i < array.size()-1; i++) {
			if(array.get(i).compareTo(array.get(i+1))> 0){
				return false;
			}
		}
		return true;
	}
}
