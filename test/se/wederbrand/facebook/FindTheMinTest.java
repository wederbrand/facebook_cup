package se.wederbrand.facebook;

import org.junit.Test;

import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

import static junit.framework.Assert.assertEquals;

public class FindTheMinTest {
	@Test
	public void testDoAll() throws Exception {
		assertEquals(
				"Case #1: 8" + System.lineSeparator() +
						"Case #2: 38" + System.lineSeparator() +
						"Case #3: 41" + System.lineSeparator() +
						"Case #4: 40" + System.lineSeparator() +
						"Case #5: 12" + System.lineSeparator(),
				FindTheMin.doAll("min_example.txt"));
	}

	@Test
	public void testCase1() throws Exception {
		assertEquals(8, FindTheMin.getValue(97, 39, 34, 37, 656, 97));
	}

	@Test
	public void testCase2() throws Exception {
		assertEquals(38, FindTheMin.getValue(186, 75, 68, 16, 539, 186));
	}

	@Test
	public void testCase3() throws Exception {
		assertEquals(41, FindTheMin.getValue(137, 49, 48, 17, 461, 137));
	}

	@Test
	public void testCase4() throws Exception {
		assertEquals(40, FindTheMin.getValue(98, 59, 6, 30, 524, 98));
	}

	@Test
	public void testCase5() throws Exception {
		assertEquals(12, FindTheMin.getValue(46, 18, 7, 11, 9, 46));
	}

	@Test
	public void testFindLow() throws Exception {
		SortedSet<Long> lowest = new TreeSet<>();
		for (long i = 0; i < 5; i++) {
			lowest.add(i);
		}

		ArrayList<Long> m = new ArrayList<>(5);
		m.add(1L);
		lowest.remove(1L);
		m.add(3L);
		lowest.remove(3L);
		m.add(0L);
		lowest.remove(0L);
		m.add(7L);
		lowest.remove(7L);

		Long first = lowest.first();
		lowest.remove(first);
		m.add(first);

		FindTheMin.rotate(m, lowest); // 3 0 7 2 1
		assertEquals(new Long(3), m.get(0));
		assertEquals(new Long(1), m.get(4));
		FindTheMin.rotate(m, lowest); // 0 7 2 1 3
		assertEquals(new Long(0), m.get(0));
		assertEquals(new Long(3), m.get(4));
		FindTheMin.rotate(m, lowest); // 7 2 1 3 0
		assertEquals(new Long(7), m.get(0));
		assertEquals(new Long(0), m.get(4));
		FindTheMin.rotate(m, lowest); // 2 1 3 0 4
		assertEquals(new Long(2), m.get(0));
		assertEquals(new Long(4), m.get(4));
		FindTheMin.rotate(m, lowest); // 1 3 0 4 2
		assertEquals(new Long(1), m.get(0));
		assertEquals(new Long(2), m.get(4));
	}
}
