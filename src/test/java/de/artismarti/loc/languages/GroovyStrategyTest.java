package de.artismarti.loc.languages;

import de.artismarti.loc.core.LOC;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author artur
 */
public class GroovyStrategyTest {

	private static final String GROOVY = "groovy";
	private static final String TEST_FILE = "./src/test/resources/Groovy.groovy";

	@Test
	public void testLogical() {
		int count = LOC.count(LanguageStrategyFactory.getInstance(GROOVY), new File(TEST_FILE));
		assertThat(count, is(3));
	}

	@Test
	public void testLogicalAndComments() {
		int count = LOC.countWithComments(LanguageStrategyFactory.getInstance(GROOVY), new File(TEST_FILE));
		assertThat(count, is(4));
	}

	@Test
	public void testLogicalAndFullWithStaticMethod() {
		int count = LOC.countWithCommentsAndImports(LanguageStrategyFactory.getInstance(GROOVY), new File(TEST_FILE));
		assertThat(count, is(5));
	}

	@Test
	public void testLogicalAndImports() {
		int count = LOC.countWithImports(LanguageStrategyFactory.getInstance(GROOVY), new File(TEST_FILE));
		assertThat(count, is(4));
	}

	@Test
	public void testLogicalAndFull() {
		int count = LOC.countWithModes(LanguageStrategyFactory.getInstance(GROOVY),
				true, true, false, false, new File(TEST_FILE));
		assertThat(count, is(5));
	}

}
