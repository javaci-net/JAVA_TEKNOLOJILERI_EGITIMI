package javaci.net;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.regex.PatternSyntaxException;

import org.junit.jupiter.api.Test;

public class MyUnitTest {

	@Test
	public void testGetTheStringArray() {
		MyUnit myUnit = new MyUnit();
		String[] expectedArray = { "one", "two", "three" };
		String[] resultArray = myUnit.getTheStringArray("one two three");
		assertArrayEquals(expectedArray, resultArray);
	}

	@Test
	public void testGetTheStringArray2() {
		MyUnit myUnit = new MyUnit();
		String[] expectedArray = { "one", "two", "three" };
		String[] resultArray = null;
		try {
			resultArray = myUnit.getTheStringArray("one?two?three", "?");
		} catch (PatternSyntaxException e) {
			return;
		}
		assertFalse(false, "Regular expression characters are not allowed");
	}

	@Test
	public void testGetTheStringArray4() {
		MyUnit myUnit = new MyUnit();
		assertThrows(PatternSyntaxException.class, () -> {
			myUnit.getTheStringArray("one?two?three", "?");
		});
	}

	@Test
	//@Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
	public void testInfiniteTametakingLoop() throws InterruptedException {
	
		assertTimeout(Duration.ofMillis(100), () -> {
			Thread.currentThread().sleep(1000);
		});
	}

	@Test
	public void testGetTheStringArray3() {
		MyUnit myUnit = new MyUnit();
		String[] resultArray = myUnit.getTheStringArray("one two three", " ");
		assertNotNull(resultArray);
		assertTrue(3 == resultArray.length);
		assertEquals("one", resultArray[0]);
		assertEquals("two", resultArray[1]);
		assertEquals("three", resultArray[2]);
	}

	@Test
	public void testConcatenate() {
		MyUnit myUnit = new MyUnit();
		String result = myUnit.concatenate("abc", "xyz");
		assertEquals("abcxyz", result);
	}

	@Test
	public void testConcatenate2() {
		MyUnit myUnit = new MyUnit();
		String result = myUnit.concatenate(null, "xyz");
		assertEquals("xyz", result);
	}

	@Test
	public void testConcatenate3() {
		MyUnit myUnit = new MyUnit();
		String result = myUnit.concatenate("abcd", null);
		assertEquals("abcd", result);
	}

	@Test
	public void testConcatenate4() {
		MyUnit myUnit = new MyUnit();
		String result = myUnit.concatenate(null, null);
		assertEquals("", result);
	}

	@Test
	public void testConcatenate5() {
		MyUnit myUnit = new MyUnit();
		String result = myUnit.concatenate("abc", "xyz", "123");
		assertEquals("abcxyz123", result);
	}

	@Test
	public void testConcatenate6() {
		MyUnit myUnit = new MyUnit();
		String result = myUnit.concatenate("abc", "xyz", "123", "a", "b");
		assertEquals("abcxyz123ab", result);
	}
}
