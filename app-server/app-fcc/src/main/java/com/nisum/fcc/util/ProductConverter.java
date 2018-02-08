package com.nisum.fcc.util;

import com.nisum.common.util.Converter;
import com.nisum.fcc.cassandra.model.Product;
import com.nisum.fcc.dto.ProductDTO;
import org.springframework.data.domain.Page;

public class ProductConverter implements Converter<Product, ProductDTO>{

	@Override
	public ProductDTO convertToDto(Product entity) {
		return ETDUtils.toProductDto(entity);
	}

	@Override
	public Product convertToEntity(ProductDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ProductDTO> convertEntitiesToDtos(Page<Product> entities) {
		// TODO Auto-generated method stub
		return null;
	}

}
