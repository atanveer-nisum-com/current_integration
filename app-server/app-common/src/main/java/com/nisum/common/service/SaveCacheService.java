package com.nisum.common.service;

public interface SaveCacheService extends CacheService{
	
	public void saveToCache(String userId, Object order);
}
