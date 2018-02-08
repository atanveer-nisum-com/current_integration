package com.nisum.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.nisum.cache.manager.EtaCacheManager;
import com.nisum.common.service.SaveCacheService;
import com.nisum.shop.service.ConsumerCacheService;

@Component
public class ConsumerCacheServiceImpl implements SaveCacheService{
	
	@Autowired
	EtaCacheManager etaCacheManager;
	
	@Autowired
	Gson gson;
	
	
	@Override
	public void saveToCache(String key, Object object) {
		try{
			etaCacheManager.writeToCache(key, gson.toJson(object).toString());
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

	@Override
	public String getFromCache(String key) {
		try{
			return etaCacheManager.readFromCache(key);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

}
