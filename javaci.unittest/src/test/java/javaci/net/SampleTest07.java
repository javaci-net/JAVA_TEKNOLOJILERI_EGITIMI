package javaci.net;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

public class SampleTest07 {

	@ParameterizedTest
	@CsvSource({
	    "apple,         1",
	    "banana,        2"
	})
	void testWithCsvSource(String fruit, int rank) {
	    assertNotNull(fruit);
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/javaci/net/test.csv", numLinesToSkip = 1)
	void testWithCsvFileSource(String country, Integer reference) {
	    assertNotNull(country);
	    assertTrue(reference > 0);
	    assertTrue(reference < 100);
	}
	
}
