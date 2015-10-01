import Exceptions.ToFewArgumentsException;
import Exceptions.UnsupportedLanguageException;
import Languages.LanguageStrategy;
import Languages.LanguageStrategyFactory;
import Languages.NullStrategy;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by artur on 10.05.15.
 * Updated by artur on 03.07.15:
 *     - Refactored countFile method, so no counting logic is in this class.
 *     - Reads now all lines from a file and provide them for the strategy.
 */
public class LocCounter {

	String fileName;
	int locCount = 0;
	boolean isCommentMode = false;
	boolean isFullMode = false;
	boolean isLocFileMode = false;
	LanguageStrategy strategy;

	public static void main(String... args) throws UnsupportedLanguageException,
			FileNotFoundException, ToFewArgumentsException {
		List<String> argList = new ArrayList<>(Arrays.asList(args));
		new LocCounter().run(argList);
	}

	public void run(List<String> argList) throws UnsupportedLanguageException,
			ToFewArgumentsException, FileNotFoundException {

		if(argList.contains("-h")) {
			System.out.println("To count lines of code of a file or a whole directory with files type:\n" +
					"java -jar LocCounter.jar [options..] [language] [pathToFile]\n" +
					"\toptions can be: \n" +
					"\t\t-c for counting comments too\n" +
					"\t\t-f for full mode, counting imports and package statements too\n" +
					"\t\t-l to create a log file with filename:loc\n" +
					"\t\t-h for help eg. this message\n" +
					"\tlanguages supported so far: \n" +
					"\t\tjava");
			return;
		}

		if(argList.contains("-c")) {
			argList.remove("-c");
			isCommentMode = true;
		}

		if(argList.contains("-f")) {
			argList.remove("-f");
			isFullMode = true;
		}

		if(argList.contains("-l")) {
			argList.remove("-l");
			isLocFileMode = true;
		}

		if(argList.isEmpty() || argList.size() < 2) {
			throw new ToFewArgumentsException("Invalid arguments! Type -h for help.");
		}

		String lang = argList.remove(0);
		strategy = getLanguageStrategy(lang);
		if(strategy instanceof NullStrategy) {
			throw new UnsupportedLanguageException("This language is not supported!");
		}

		File file = new File(argList.remove(0));
		if(!file.exists()) {
			throw new FileNotFoundException("The file you entered doesn't exist.");
		}

		fileName = file.getName();

		countDir(file);
		System.out.println(fileName + " : " + locCount);
	}

	private LanguageStrategy getLanguageStrategy(String lang) {
		return LanguageStrategyFactory.getInstance(lang);
	}

	private void countDir(File dir) {
		if(dir.isDirectory()) {
			for(File file : dir.listFiles()) {
				countDir(file);
			}
		} else if(dir.isFile()) {
			countFile(dir);
		}
	}

	private void countFile(File file) {
		int locForFile = 0;

		if(!strategy.isLangOfFileSame(file.getName())) {
			return;
		}

		try {
			List<String> lines = Files.readAllLines(file.toPath());
				locForFile += strategy.analyze(lines, isCommentMode, isFullMode);
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}

		locCount += locForFile;

		if(isLocFileMode) {
			printLocFile(file.getName(), locForFile);
		}
	}

	private void printLocFile(String filename, int count) {
		Path path = Paths.get("./" + fileName + ".txt");
		String append = filename + " : " + count + "\n";

		BufferedWriter writer = null;
		try {
			writer = Files.newBufferedWriter(path, Charset.defaultCharset(),
					StandardOpenOption.CREATE, StandardOpenOption.APPEND);
			writer.write(append, 0, append.length());
			writer.close();
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		} finally {
			if(writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					System.err.format("IOException: %s%n", e);
				}
			}
		}
	}

	public int getLocCount() {
		return locCount;
	}
}
