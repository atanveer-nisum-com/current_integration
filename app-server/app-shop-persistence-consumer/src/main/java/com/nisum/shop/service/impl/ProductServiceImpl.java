package com.nisum.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nisum.common.constant.CommonEndPointConstant;
import com.nisum.common.shop.dto.ProductDTO;
import com.nisum.shop.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	RestTemplate restClient;
	
	
	@Override
	public ProductDTO findOne(String id) {
		ProductDTO productDto = restClient.getForObject(CommonEndPointConstant.PRODUCT_FIND_ONE, ProductDTO.class, id);
		return productDto;
	}
	
	
	@Override
	public ProductDTO findAvailable(String id) {
		ProductDTO productDto = restClient.getForObject(CommonEndPointConstant.CART_PRODUCT_FIND_ONE, ProductDTO.class, id);
		return productDto;
	}

}
