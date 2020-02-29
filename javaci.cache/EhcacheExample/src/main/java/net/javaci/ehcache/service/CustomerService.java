package net.javaci.ehcache.service;

import org.ehcache.Cache;

import net.javaci.ehcache.CacheNames;
import net.javaci.ehcache.entity.Customer;
import net.javaci.ehcache.helpers.AbstractCacheManager;

public class CustomerService extends AbstractService {
	
	private Cache<Integer, Customer> customerCache;

	public CustomerService(AbstractCacheManager cahce) {
		super(cahce);
		customerCache = cahce.getInstance().getCache(CacheNames.CUSTOMER_CACHE, Integer.class, Customer.class);
	}

	public Customer getCustomerByIdFromCache(int id) {

		if (customerCache.containsKey(id)) {
			return customerCache.get(id);
		}

		Customer customer = getCustomerById(id);
		customerCache.put(id, customer);

		return customer;
	}

	public Customer getCustomerById(int id) {
		Customer customer = new Customer(id, "Customer" + id);

		try {
			// delay for simulating time for DB fetching
			Thread.sleep(1);
		} catch (InterruptedException e) {		}
		
		return customer;
	}

}
