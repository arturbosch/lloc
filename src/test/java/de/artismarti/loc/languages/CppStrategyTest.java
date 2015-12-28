package de.artismarti.loc.languages;

import de.artismarti.loc.core.LOC;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author artur
 */
public class CppStrategyTest {

	@Test
	public void testLogical() {
		int count = LOC.count(LanguageStrategyFactory.getInstance("cpp"),
				new File("./src/test/resources/test.cpp"));
		assertThat(count, is(3));
	}

	@Test
	public void testLogicalAndComments() {
		int count = LOC.countWithComments(LanguageStrategyFactory.getInstance("cpp"),
				new File("./src/test/resources/test.cpp"));
		assertThat(count, is(4));
	}

	@Test
	public void testLogicalAndFull() {
		int count = LOC.countWithModes(LanguageStrategyFactory.getInstance("cpp"),
				true, true, false, false, new File("./src/test/resources/test.cpp"));
		assertThat(count, is(6));
	}

}
