package se.wederbrand.facebook;

import org.junit.Test;

import static junit.framework.Assert.*;

public class BalancedSmileysTest {
	@Test
	public void testDoAll() throws Exception {
		assertEquals("Case #1: NO\n" +
				"Case #2: YES\n" +
				"Case #3: YES\n" +
				"Case #4: YES\n" +
				"Case #5: NO\n", BalancedSmileys.doAll("smiley_example.txt"));
	}

	@Test
	public void testCase1() throws Exception {
		assertFalse(BalancedSmileys.isBalanced(":(("));
	}

	@Test
	public void testCase2() throws Exception {
		assertTrue(BalancedSmileys.isBalanced("i am sick today (:()"));
	}

	@Test
	public void testCase3() throws Exception {
		assertTrue(BalancedSmileys.isBalanced("(:)"));
	}

	@Test
	public void testCase4() throws Exception {
		assertTrue(BalancedSmileys.isBalanced("hacker cup: started :):)"));
	}

	@Test
	public void testCase5() throws Exception {
		assertFalse(BalancedSmileys.isBalanced(")("));
	}

	@Test
	public void testCleanUp() throws Exception {
		assertEquals("]", BalancedSmileys.cleanUp(":)"));
		assertEquals("[", BalancedSmileys.cleanUp(":("));
		assertEquals("X", BalancedSmileys.cleanUp("(:)"));
		assertEquals("]", BalancedSmileys.cleanUp("abc:)def"));
		assertEquals("()][()X", BalancedSmileys.cleanUp("(a)b:)c:(d(e)(:)"));
	}

	public static class BeautifulStringsTest {
		@Test
		public void testDoAll() throws Exception {
			assertEquals(
					"Case #1: 152" + System.lineSeparator() +
							"Case #2: 754" + System.lineSeparator() +
							"Case #3: 491" + System.lineSeparator() +
							"Case #4: 729" + System.lineSeparator() +
							"Case #5: 646" + System.lineSeparator(),
					BeautifulStrings.doAll("example.txt"));
		}

		@Test
		public void testCase1() throws Exception {
			assertEquals(152, BeautifulStrings.getMax("ABbCcc"));
		}

		@Test
		public void testCase2() throws Exception {
			assertEquals(754, BeautifulStrings.getMax("Good luck in the Facebook Hacker Cup this year!"));
		}

		@Test
		public void testCase3() throws Exception {
			assertEquals(491, BeautifulStrings.getMax("Ignore punctuation, please :)"));
		}

		@Test
		public void testCase4() throws Exception {
			assertEquals(729, BeautifulStrings.getMax("Sometimes test cases are hard to make up."));
		}

		@Test
		public void testCase5() throws Exception {
			assertEquals(646, BeautifulStrings.getMax("So I just go consult Professor Dalves"));
		}
	}
}
