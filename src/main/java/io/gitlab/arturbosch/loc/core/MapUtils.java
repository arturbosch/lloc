package io.gitlab.arturbosch.loc.core;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Provides static methods to sort entry sets.
 * <p>
 * Created by artur on 11.10.15.
 */
class MapUtils {

	private MapUtils() {
	}

	static Comparator<Map.Entry<String, Integer>> byValues = Map.Entry.comparingByValue(Comparator.reverseOrder());

	static Comparator<Map.Entry<String, Integer>> byKeys = Map.Entry.comparingByKey();

	static Map<String, Integer> sortMap(Map<String, Integer> unsorted, Comparator<Map.Entry<String, Integer>> comparator) {
		return unsorted.entrySet()
				.stream()
				.sorted(comparator)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}
}
