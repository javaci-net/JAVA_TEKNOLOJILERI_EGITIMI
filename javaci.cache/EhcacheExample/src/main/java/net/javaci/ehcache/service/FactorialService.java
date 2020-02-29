package net.javaci.ehcache.service;

import org.ehcache.Cache;

import net.javaci.ehcache.CacheNames;
import net.javaci.ehcache.helpers.AbstractCacheManager;

public class FactorialService extends AbstractService {

	private Cache<Long, Long> factorialCache;

	public FactorialService(AbstractCacheManager cahce) {
		super(cahce);
		factorialCache = cahce.getInstance().getCache(CacheNames.FACTORIAL_CACHE, Long.class, Long.class);
	}

	public long getFactorialFromCache(long input) {

		if (factorialCache.containsKey(input)) {
			return factorialCache.get(input);
		}

		long factorialValue = getFactorial(input);
		factorialCache.put(input, factorialValue);

		return factorialValue;
	}

	public long getFactorial(long input) {
		long result = 1;
		for (long i = 1; i < input; i++) {
			result *= i;
		}
		return result;
	}

}
