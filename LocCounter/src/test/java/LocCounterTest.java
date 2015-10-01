import Exceptions.ToFewArgumentsException;
import Exceptions.UnsupportedLanguageException;
import Languages.JavaStrategy;
import Languages.LanguageStrategy;
import Languages.LanguageStrategyFactory;
import Languages.NullStrategy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.Is.isA;
import static org.junit.Assert.assertTrue;

/**
 * Created by artur on 10.05.15.
 */
public class LocCounterTest {

	private LocCounter locCounter;

	@Before
	public void setUp() {
		locCounter = new LocCounter();
	}

	@Test
	public void testHelpMode() throws UnsupportedLanguageException, ToFewArgumentsException, FileNotFoundException {
		LocCounter.main("-h");
	}

	@Test(expected = ToFewArgumentsException.class)
	public void testModes() throws UnsupportedLanguageException, ToFewArgumentsException, FileNotFoundException {
		String[] args = {"-c","-l"};
		LocCounter.main(args);
		Assert.assertThat(locCounter.isCommentMode, is(true));
		Assert.assertThat(locCounter.isLocFileMode, is(true));
	}

	@Test(expected = UnsupportedLanguageException.class)
	public void testUnsupportedLanguage() throws UnsupportedLanguageException, ToFewArgumentsException, FileNotFoundException {
		String[] args = {"-c","-l","xml","file"};
		LocCounter.main(args);
	}

	@Test(expected = FileNotFoundException.class)
	public void testFileNotExist() throws UnsupportedLanguageException, ToFewArgumentsException, FileNotFoundException {
		String[] args = {"-c","-l","java","file"};
		LocCounter.main(args);
	}

	@Test
	public void testFileCount() {
		String[] args = {"java","./src/main/java/Exceptions/UnsupportedLanguageException.java"};
		int expectedCount = 3;
		assertLocCount(expectedCount, args);
	}

	@Test
	public void testDirectoryCount() {
		String[] args = {"-l","java","./src"};
		int expectedCount = 150;
		assertLocCount(expectedCount, args);
		File file = new File("src.txt");
		Assert.assertThat("Loc file doesn't exist!", file.exists(), is(true));
	}

	@Test
	public void testDirectoryCountWithComments() {
		String[] args = {"-c", "-l","java","./src"};
		int expectedCount = 170;
		assertLocCount(expectedCount, args);
		File file = new File("src.txt");
		Assert.assertThat("Loc file doesn't exist!", file.exists(), is(true));
	}

	@Test
	public void testDirectoryCountWithFullMode() {
		String[] args = {"-f", "-l","java","./src"};
		int expectedCount = 200;
		assertLocCount(expectedCount, args);
		File file = new File("src.txt");
		Assert.assertThat("Loc file doesn't exist!", file.exists(), is(true));
	}

	@Test
	public void testDirectoryCountWithFullAndCommentMode() {
		String[] args = {"-c", "-f", "-l","java","./src"};
		int expectedCount = 220;
		assertLocCount(expectedCount, args);
		File file = new File("src.txt");
		Assert.assertThat("Loc file doesn't exist!", file.exists(), is(true));
	}


	private void assertLocCount(int exceptedCount, String[] args) {
		try {
			locCounter.run(new ArrayList<>(Arrays.asList(args)));
			assertTrue(locCounter.getLocCount() >= exceptedCount);
		} catch (Exception e) {
			Assert.assertThat("An exception occurred, but file should exist.", true, is(false));
		}
	}

	@Test
	public void testGetLanguageStrategy() {
		String lang = "java";
		LanguageStrategy ls = LanguageStrategyFactory.getInstance(lang);
		assertThat(ls, isA(LanguageStrategy.class));
		assertThat(ls.getEnding(), is(lang));
		assertTrue(ls instanceof JavaStrategy);
	}

	@Test
	public void testGetNullStrategy() {
		LanguageStrategy ls = LanguageStrategyFactory.getInstance("meow");
		assertThat(ls, isA(LanguageStrategy.class));
		assertTrue(ls instanceof NullStrategy);
	}
}
