package net.javaci.ehcache.helpers;

import org.ehcache.CacheManager;

public abstract class AbstractCacheManager {
	
	protected final CacheManager INSTANCE;
	
	public AbstractCacheManager() {
		INSTANCE = initCache();
	}
	
	public CacheManager getInstance() {
		return INSTANCE;
	}
	
	protected abstract CacheManager initCache();
	
	public void removeCache(String cacheName) {
		getInstance().removeCache(cacheName);
	}

	public void closeCacheManager() {
			getInstance().close();
	}
}
