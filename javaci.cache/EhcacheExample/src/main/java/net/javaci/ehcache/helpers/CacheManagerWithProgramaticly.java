package net.javaci.ehcache.helpers;

import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

import net.javaci.ehcache.CacheNames;
import net.javaci.ehcache.entity.Customer;

public class CacheManagerWithProgramaticly extends AbstractCacheManager {

	public CacheManager initCache() {
		
		CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
				.withCache(CacheNames.FACTORIAL_CACHE, CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class,
						Long.class, ResourcePoolsBuilder.heap(5_000)))
				.build();
		cacheManager.init();

		cacheManager.createCache(CacheNames.CUSTOMER_CACHE, CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class,
				Customer.class, ResourcePoolsBuilder.heap(5_000)));
		
		return cacheManager;

	}

}
