package com.nisum.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.nisum.cache.manager.CacheManager1;
import com.nisum.common.service.SaveCacheService;

@Component
public class CacheServiceImpl implements SaveCacheService {

	@Autowired
	CacheManager1 cacheManager;

	@Autowired
	Gson gson;

	@Override
	public void saveToCache(String key, Object object) {
		try {
			cacheManager.writeToCache(key, gson.toJson(object).toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public String getFromCache(String key) {
		try {
			return cacheManager.readFromCache(key);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
