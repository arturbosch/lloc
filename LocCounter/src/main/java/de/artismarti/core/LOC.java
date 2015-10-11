package de.artismarti.core;

import de.artismarti.languages.LanguageStrategy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Logic to count LOCs.
 * <p>
 * Created by artur on 11.10.15.
 */
public class LOC {

	public static final String ISO_8859_1 = "ISO-8859-1";
	String fileName;
	boolean isCommentMode;
	boolean isFullMode;
	boolean isLocFileMode;

	private LOC(String name, boolean isCommentMode, boolean isFullMode, boolean isLocFileMode) {
		this.fileName = name;
		this.isCommentMode = isCommentMode;
		this.isFullMode = isFullMode;
		this.isLocFileMode = isLocFileMode;
	}

	/**
	 * Counts the LOC for all given files with the given language. No comments and import, package statements are counted.
	 *
	 * @param strategy use {@link de.artismarti.languages.LanguageStrategyFactory} to build a language strategy
	 * @param files    which files should be analyzed
	 * @return loc count of all analyzed files
	 */
	public static int count(LanguageStrategy strategy, File... files) {
		return countWithModes(strategy, false, false, false, files);
	}

	/**
	 * Counts the LOC for all given files with the given language. Comments are counted too but not import or package statements.
	 *
	 * @param strategy use {@link de.artismarti.languages.LanguageStrategyFactory} to build a language strategy
	 * @param files    which files should be analyzed
	 * @return loc count of all analyzed files
	 */
	public static int countWithComments(LanguageStrategy strategy, File... files) {
		return countWithModes(strategy, true, false, false, files);
	}

	/**
	 * Counts the LOC for all given files with the given language.
	 *
	 * @param strategy      use {@link de.artismarti.languages.LanguageStrategyFactory} to build a language strategy
	 * @param isCommentMode if true, count comments too
	 * @param isFullMode    if true, count imports and package statements too
	 * @param isLocFileMode if true, create file with loc to file entries
	 * @param files         which files should be analyzed
	 * @return loc count of all analyzed files
	 */
	public static int countWithModes(LanguageStrategy strategy, boolean isCommentMode, boolean isFullMode,
	                                 boolean isLocFileMode, File... files) {
		int counter = 0;
		for (File file : files) {
			counter += new LOC(file.getName(), isCommentMode, isFullMode, isLocFileMode).countDir(file, strategy);
		}
		return counter;
	}

	private int countDir(File dir, LanguageStrategy strategy) {
		int loc = 0;
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			if (files != null) {
				for (File file : files) {
					loc += countDir(file, strategy);
				}
			}
		} else if (dir.isFile()) {
			loc += countFile(dir, strategy);
		}
		return loc;
	}

	private int countFile(File file, LanguageStrategy strategy) {
		int locForFile = 0;

		if (!strategy.isLangOfFileSame(file.getName())) {
			return locForFile;
		}

		try {
			List<String> lines = Files.readAllLines(file.toPath(), Charset.forName(ISO_8859_1));
			locForFile += strategy.analyze(lines, isCommentMode, isFullMode);
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}

		if (isLocFileMode) {
			printLocFile(file.getName(), locForFile);
		}

		return locForFile;
	}

	private void printLocFile(String filename, int count) {
		Path path = Paths.get("./" + fileName + ".txt");
		String append = filename + " : " + count + "\n";

		try (BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName(ISO_8859_1),
				StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
			writer.write(append, 0, append.length());
			writer.close();
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
	}

}
