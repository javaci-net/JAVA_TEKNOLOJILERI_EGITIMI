package javaci.net;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BusinessTest {
	Business business;
	PersonDAOMock mockDao = new PersonDAOMock();
	
	@BeforeEach
	public void before() {
		business = new Business();
		business.dao = mockDao;
		
	}
	
	@Test
	public void testAvgNameLen() {
		double avg = business.calculateAverageNameLength();
		assertEquals(5.0, avg);
	}
	
	@Test
	public void testAvgNameLen2() {
		mockDao.findAllResult = new ArrayList<Person>();
		double avg = business.calculateAverageNameLength();
		assertEquals(0.0, avg);
	}
}
