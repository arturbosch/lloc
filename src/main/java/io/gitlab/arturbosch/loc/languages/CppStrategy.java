package io.gitlab.arturbosch.loc.languages;

/**
 * Counts loc of a cpp file. Due to similar syntax to java, this class extends the java strategy.
 *
 * @author artur
 */
class CppStrategy extends JavaStrategy {

	private String[] endings = {"cpp", "c", "cc", "c++", "h", "h++", "hpp"};

	CppStrategy(String ending) {
		super(ending);
		comments = new String[]{"//", "/*", "*/", "*"};
		escapes = new String[]{"#include", "using"};
	}

	@Override
	public boolean isLangOfFileSame(String filename) {
		for (String ending : endings) {
			if (filename.endsWith(ending)) {
				return true;
			}
		}
		return false;
	}

}
