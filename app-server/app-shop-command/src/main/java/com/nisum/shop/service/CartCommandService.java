package com.nisum.shop.service;

public interface CartCommandService {
	public void addToCart(String itemId);
	public void addToCart(Long userId, String itemId);
}
