package javaci.net;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.condition.OS.LINUX;
import static org.junit.jupiter.api.condition.OS.MAC;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;


@DisplayName("DisplayName kullanim ornegi")
public class SampleTest03 {

	@DisplayName("Bos Test 1")
	@Test
	public void test1() {
		assertTrue(true);
	}

	@DisplayName("Bos Test 2")
	@Test
	public void test2() {
		assertTrue(true);
	}

	@Disabled("Bug#101 cozulesiye kadar disable olarak tutuluyor")
	@Test
	private void test3() {
		assertTrue(false);
	}

	@EnabledOnOs({ LINUX, MAC })
	@Test
	public void test4() {
		assertTrue(true);
	}
	
	@DisabledOnOs({ MAC })
	@Test
	public void test5() {
		assertTrue(true);
	}
	
	@DisabledIfSystemProperty(named = "ci-server", matches = "true")
	@Test
	public void test6() {
		assertTrue(true);
	}
}
