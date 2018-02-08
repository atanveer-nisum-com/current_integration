package com.nisum.shop.service;

import com.nisum.common.service.BaseServiceLocator;
import com.nisum.shop.model.Order;

public interface OrderPersistenceService extends BaseServiceLocator{
	Order getActiveOrderByUserId(Long userId);
	Order updateRepository(Order order);
	Order getFromCache(String cacheOrderKey);
}
