package com.nisum.shop.query.service;

import com.nisum.shop.dto.CartResponseDTO;

public interface CartQueryService {
	public CartResponseDTO getCartResponse(Long userId, String orderId);
}
