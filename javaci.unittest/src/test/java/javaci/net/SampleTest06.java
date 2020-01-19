package javaci.net;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class SampleTest06 {

	
	@ParameterizedTest
	@MethodSource("stringProvider")
	public void testWithExplicitLocalMethodSource(String argument) {
	    assertNotNull(argument);
	}

	static Stream<String> stringProvider() {
	    return Stream.of("apple", "banana");
	}
	
	@ParameterizedTest
	@MethodSource("stringIntAndListProvider")
	public void testWithMultiArgMethodSource(String str, int num, List<String> list) {
	    assertEquals(5, str.length());
	    assertTrue(num >=1 && num <=2);
	    assertEquals(2, list.size());
	}

	public static Stream<Arguments> stringIntAndListProvider() {
	    return Stream.of(
	        Arguments.of("apple", 1, Arrays.asList("a", "b")),
	        Arguments.of("lemon", 2, Arrays.asList("x", "y"))
	    );
	}

}
