package com.nisum.fcc.util;

import com.nisum.common.util.Converter;
import com.nisum.fcc.cassandra.model.Product;
import com.nisum.fcc.dto.ShopProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ShopProductConverter implements Converter<Product, ShopProductDTO>{

	@Override
	public ShopProductDTO convertToDto(Product entity) {
		return ETDUtils.toShopProductDto(entity);
	}

	@Override
	public Product convertToEntity(ShopProductDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ShopProductDTO> convertEntitiesToDtos(Page<Product> entities) {
		// TODO Auto-generated method stub
		return null;
	}

}
