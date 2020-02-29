package net.javaci.ehcache;

import java.util.Random;

import net.javaci.ehcache.helpers.AbstractCacheManager;
import net.javaci.ehcache.helpers.CacheManagerWithProgramaticly;
import net.javaci.ehcache.helpers.CacheManagerWithXMLConfig;
import net.javaci.ehcache.service.CustomerService;
import net.javaci.ehcache.service.FactorialService;

public class Main {
	private static Random rand = new Random();
	
	
	public static void main(String[] args) {
			AbstractCacheManager acm;
		
		
		acm = new CacheManagerWithProgramaticly();
		testTimeForFactorial(acm);
		testTimeForCustomer(acm);
		System.out.println("-------------------------");
		
		acm = new CacheManagerWithXMLConfig();
		testTimeForFactorial(acm);
		testTimeForCustomer(acm);
		System.out.println("-------------------------");
		
		
		acm = new CacheManagerWithXMLConfig();
		testTimeForFactorial(acm);
		System.out.println("-------------------------");

	}

	private static void testTimeForFactorial(AbstractCacheManager cmp) {
		int numberOfRequestedTransationForFactorial = 1_000_000;
		int factorialUpperLimit = 5000;
		
		FactorialService fs = new FactorialService(cmp);

		long start = System.currentTimeMillis();
		for (int i = 0; i < numberOfRequestedTransationForFactorial; i++) {
			int randNumber = rand.nextInt(factorialUpperLimit);
			fs.getFactorialFromCache(randNumber);
		}
		long end = System.currentTimeMillis();
		System.out.println("Getting factorials takes: " + (end - start) + " ms with using cache " + cmp.getClass().getSimpleName());

		start = System.currentTimeMillis();
		for (int i = 0; i < numberOfRequestedTransationForFactorial; i++) {
			int randNumber = rand.nextInt(factorialUpperLimit);
			fs.getFactorial(randNumber);
		}
		end = System.currentTimeMillis();
		System.out.println("Getting factorials takes: " + (end - start) + " ms without using cache " + cmp.getClass().getSimpleName());
	}
	
	private static void testTimeForCustomer(AbstractCacheManager cmp) {
		int numberOfRequestedTransationForCutomer = 15_000;
		int customerIdUpperLimit = 5_000;
		
		CustomerService cs = new CustomerService(cmp);

		long start = System.currentTimeMillis();
		for (int i = 0; i < numberOfRequestedTransationForCutomer; i++) {
			int randNumber = rand.nextInt(customerIdUpperLimit);
			cs.getCustomerByIdFromCache(randNumber);
		}
		long end = System.currentTimeMillis();
		System.out.println("Getting customers takes: " + (end - start) + " ms with using cache with " + cmp.getClass().getSimpleName());

		start = System.currentTimeMillis();
		for (int i = 0; i < numberOfRequestedTransationForCutomer; i++) {
			int randNumber = rand.nextInt(customerIdUpperLimit);
			cs.getCustomerById(randNumber);
		}
		end = System.currentTimeMillis();
		System.out.println("Getting customers takes: " + (end - start) + " ms without using cache with " + cmp.getClass().getSimpleName());
	}

}
