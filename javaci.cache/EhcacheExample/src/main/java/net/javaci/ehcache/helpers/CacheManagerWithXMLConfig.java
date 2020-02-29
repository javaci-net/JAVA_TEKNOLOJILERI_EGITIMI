package net.javaci.ehcache.helpers;

import java.net.URL;

import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;

public class CacheManagerWithXMLConfig extends AbstractCacheManager {

	@Override
	protected CacheManager initCache() {

		URL myUrl = getClass().getClassLoader().getResource("myEhcache.xml");
		Configuration xmlConfig = new XmlConfiguration(myUrl);
		CacheManager cacheManager = CacheManagerBuilder.newCacheManager(xmlConfig);
		
		cacheManager.init();
		
		return cacheManager;
	}
}
