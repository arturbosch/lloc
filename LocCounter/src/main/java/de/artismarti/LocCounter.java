package de.artismarti;

import de.artismarti.exceptions.ToFewArgumentsException;
import de.artismarti.exceptions.UnsupportedLanguageException;
import de.artismarti.languages.LanguageStrategy;
import de.artismarti.languages.LanguageStrategyFactory;
import de.artismarti.languages.NullStrategy;
import de.artismarti.core.LOC;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by artur on 10.05.15.
 * Updated by artur on 03.07.15:
 * - Refactored countFile method, so no counting logic is in this class.
 * - Reads now all lines from a file and provide them for the strategy.
 */
public class LocCounter {

	String fileName;
	int locCount = 0;
	boolean isCommentMode = false;
	boolean isFullMode = false;
	boolean isLocFileMode = false;

	public static void main(String... args) throws UnsupportedLanguageException,
			FileNotFoundException, ToFewArgumentsException {
		List<String> argList = new ArrayList<>(Arrays.asList(args));
		new LocCounter().run(argList);
	}

	/**
	 * To count lines of code of a file or a whole directory with files write:
	 * <p>
	 * "java -jar CountMyLOCs.jar [options..] [language] [pathToFile]"
	 * <p>
	 * options can be:
	 * -c for counting comments too\n" +
	 * -f for full mode, counting imports and package statements too
	 * -l to create a log file with entries look like filename:loc
	 * -h for help eg. this message
	 * languages supported so far:
	 * java
	 *
	 * @param arguments list of arguments
	 * @throws UnsupportedLanguageException if the given language is not supported
	 * @throws ToFewArgumentsException      if not all needed arguments are given
	 * @throws FileNotFoundException        if the given file/dir doesn't exist
	 */
	void run(List<String> arguments) throws UnsupportedLanguageException,
			ToFewArgumentsException, FileNotFoundException {

		List<String> argList = new ArrayList<>(arguments);

		if (argList.contains("-h")) {
			System.out.println("To count lines of code of a file or a whole directory with files write:\n" +
					"java -jar CountMyLOCs.jar [options..] [language] [pathToFile]\n" +
					"\toptions can be: \n" +
					"\t\t-c for counting comments too\n" +
					"\t\t-f for full mode, counting imports and package statements too\n" +
					"\t\t-l to create a log file with filename:loc\n" +
					"\t\t-h for help eg. this message\n" +
					"\tlanguages supported so far: \n" +
					"\t\tjava");
			return;
		}

		if (argList.contains("-c")) {
			argList.remove("-c");
			isCommentMode = true;
		}

		if (argList.contains("-f")) {
			argList.remove("-f");
			isFullMode = true;
		}

		if (argList.contains("-l")) {
			argList.remove("-l");
			isLocFileMode = true;
		}

		if (argList.isEmpty() || argList.size() < 2) {
			throw new ToFewArgumentsException("Invalid arguments! Type -h for help.");
		}

		String lang = argList.remove(0);
		LanguageStrategy strategy = LanguageStrategyFactory.getInstance(lang);
		if (strategy instanceof NullStrategy) {
			throw new UnsupportedLanguageException("This language is not supported!");
		}

		File file = new File(argList.remove(0));
		if (!file.exists()) {
			throw new FileNotFoundException("The file you entered doesn't exist.");
		}

		fileName = file.getName();

		int locCount = LOC.countWithModes(strategy, isCommentMode, isFullMode, isLocFileMode, file);
		this.locCount = locCount;
		System.out.println(fileName + " : " + locCount);
	}

	int getLocCount() {
		return locCount;
	}
}
