package net.javaci.ehcache.service;

import net.javaci.ehcache.helpers.AbstractCacheManager;

public abstract class AbstractService {
	
	protected AbstractCacheManager cahce;

	public AbstractService(AbstractCacheManager cahce) {
		super();
		this.cahce = cahce;
	}
	
	
}