package com.nisum.fcc.cassandra.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nisum.fcc.cassandra.model.Product;
import com.nisum.fcc.cassandra.repository.ProductRepository;
import com.nisum.fcc.constant.AppConstantConfig;
import com.nisum.fcc.dto.ProductDTO;
import com.nisum.fcc.dto.ShopProductDTO;
import com.nisum.fcc.service.ProductService;
import com.nisum.fcc.util.ProductConverter;
import com.nisum.fcc.util.ShopProductConverter;

@Service("productCassandraService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productCassandraRepository;

	@Autowired
	private AppConstantConfig appConstant;
	
	private ProductConverter productConverter = new ProductConverter();
	
	private ShopProductConverter shopConverter = new ShopProductConverter();

	@Override
	public List<ProductDTO> getAllByPage(Pageable pageable) throws Exception {
		int size = pageable.getPageSize();
		int page = pageable.getPageNumber();
		return converter(productCassandraRepository.findWithLimit((size*page)+1,(size*(page+1))));
	}

	@Override
	public Page<ProductDTO> getProductByCategory(Object categoryId, Pageable pageable) {
		return new PageImpl<ProductDTO>(converter(productCassandraRepository.getProductByCategory(categoryId, pageable)));
	}
	
	@Override
	public ProductDTO findByUniqueIdentifier(String uniqeIdentifier) {
				
		if(uniqeIdentifier.indexOf(appConstant.getPRODUCT_ID_TOKINIZER()) == -1) {
			return null;
		}
		
		String strRaw[] = uniqeIdentifier.split(appConstant.getPRODUCT_ID_TOKINIZER());
		String code = strRaw[0];
		String name = strRaw[1];
		
		return convertSingleProduct(productCassandraRepository.getProductByCodeAndName(code, name));
	}
	
	@Override
	public ProductDTO getProductById(UUID itemId, Optional<Boolean> isForCart) {
		if(null != isForCart && isForCart.isPresent()){
			return convertSingleProduct(productCassandraRepository.findActiveProductForCartById(itemId));
		}
			return convertSingleProduct(productCassandraRepository.findById(itemId));

	}
	
	@Override
	public ShopProductDTO getProductById(UUID itemId) {
		return convertToShopProductDto(productCassandraRepository.findById(itemId));
	}

	@Override
	public List<ProductDTO> searchProductByName(String name) {
//		String wildCharStart = "'%";
//		String wildCharEnd = "%'";
//		name = wildCharStart +name + wildCharEnd;
		return converter(productCassandraRepository.findBySearchStrContaining(name));
	}
	
	private List<ProductDTO> converter(List<Product> data) {
		return data.stream().map(prod -> productConverter.convertToDto(prod))
		.collect(Collectors.toList());
	}	
	
	private ProductDTO convertSingleProduct(Product data) {
		return productConverter.convertToDto(data);
	}
	
	private ShopProductDTO convertToShopProductDto(Product product) {
		return shopConverter.convertToDto(product);
	}

}
