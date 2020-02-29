package net.javaci.ehcache.helpers;

import org.ehcache.CacheManager;
import org.ehcache.PersistentCacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;

import net.javaci.ehcache.CacheNames;

public class CacheManagerWithPersistence extends AbstractCacheManager {

	@Override
	protected CacheManager initCache() {
		PersistentCacheManager persistentCacheManager = CacheManagerBuilder.newCacheManagerBuilder()
				.with(CacheManagerBuilder.persistence("factorialValue.cache"))
				.withCache(CacheNames.FACTORIAL_CACHE,
						CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, Integer.class,
								ResourcePoolsBuilder.newResourcePoolsBuilder().heap(10, EntryUnit.ENTRIES).disk(10,
										MemoryUnit.MB, true)))
				.build(true);
		persistentCacheManager.init();

		return persistentCacheManager;
	}

}
