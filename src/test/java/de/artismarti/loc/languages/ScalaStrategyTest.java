package de.artismarti.loc.languages;

import de.artismarti.loc.core.LOC;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author artur
 */
public class ScalaStrategyTest {

	private static final String SCALA = "scala";
	private static final String TEST_FILE = "./src/test/resources/Scala.scala";

	@Test
	public void testLogical() {
		int count = LOC.count(LanguageStrategyFactory.getInstance(SCALA), new File(TEST_FILE));
		assertThat(count, is(3));
	}

	@Test
	public void testLogicalAndComments() {
		int count = LOC.countWithComments(LanguageStrategyFactory.getInstance(SCALA), new File(TEST_FILE));
		assertThat(count, is(4));
	}

	@Test
	public void testLogicalAndFullWithStaticMethod() {
		int count = LOC.countWithCommentsAndImports(LanguageStrategyFactory.getInstance(SCALA), new File(TEST_FILE));
		assertThat(count, is(5));
	}

	@Test
	public void testLogicalAndImports() {
		int count = LOC.countWithImports(LanguageStrategyFactory.getInstance(SCALA), new File(TEST_FILE));
		assertThat(count, is(4));
	}

	@Test
	public void testLogicalAndFull() {
		int count = LOC.countWithModes(LanguageStrategyFactory.getInstance(SCALA),
				true, true, false, false, new File(TEST_FILE));
		assertThat(count, is(5));
	}

}
