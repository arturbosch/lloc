package de.artismarti.core;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MapUtilsTest {

	@Test
	public void sortMapByValues() {
		Map<String, Integer> map = new HashMap<>();
		map.put("1", 2);
		map.put("2", 1);
		map.put("3", 3);
		Map<String, Integer> sortedMap = MapUtils.sortMap(map, MapUtils.byValues);
		final int[] counter = {3};
		sortedMap.forEach((key, value) -> assertThat(value, is(counter[0]--)));
	}

	@Test
	public void sortMapByKeys() {
		Map<String, Integer> map = new HashMap<>();
		map.put("c", 1);
		map.put("b", 2);
		map.put("a", 3);
		Map<String, Integer> sortedMap = MapUtils.sortMap(map, MapUtils.byKeys);
		final int[] counter = {3};
		sortedMap.forEach((key, value) -> assertThat(value, is(counter[0]--)));
	}
}