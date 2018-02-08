package com.nisum.shop.service;

import com.nisum.common.service.CacheService;

public interface ConsumerCacheService extends CacheService {
	
	public void saveToCache(String userId, Object order);
	
}
