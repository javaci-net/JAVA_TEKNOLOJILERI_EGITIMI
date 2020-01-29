package javaci.net;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

//@TestInstance(Lifecycle.PER_CLASS)
public class SampleTest02 {
	
	static private int i;
	
	@BeforeAll
    static void initAll() {
		System.out.println("@BeforeAll");
    }

    @BeforeEach
    void init() {
    	i=0;
    	System.out.println("@BeforeEach is running");
    }

    @Test
    void succeedingTest1() {
    	i++;
    	System.out.println("Ornek test case 1 calisiyor:" + i);
    }

    @Test
    void succeedingTest2() {
    	i++;
    	System.out.println("Ornek test case 2 calisiyor" + i);
    }

    @AfterEach
    void tearDown() {
    	System.out.println("@AfterEach is running");
    }

    @AfterAll
    static void tearDownAll() {
    	
    	System.out.println("@AfterAll is running");
    }
}
