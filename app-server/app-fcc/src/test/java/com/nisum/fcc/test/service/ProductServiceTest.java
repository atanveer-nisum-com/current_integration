package com.nisum.fcc.test.service;

import com.nisum.common.constant.ErrorLevel;
import com.nisum.common.exception.rest.RestException;
import com.nisum.fcc.cassandra.model.Product;
import com.nisum.fcc.cassandra.repository.ProductRepository;
import com.nisum.fcc.cassandra.service.impl.ProductServiceImpl;
import com.nisum.fcc.constant.AppConstantConfig;
import com.nisum.fcc.dto.ProductDTO;
import com.nisum.fcc.service.ProductService;
import com.nisum.fcc.util.ETDUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyInt;

public class ProductServiceTest extends BaseServiceTest {

	@Autowired
	private ProductService productService;
	@MockBean
	private ProductRepository productRepository;

	@Autowired
	private AppConstantConfig appConfig;

	private Product product;
	
	Pageable page = new Pageable() {
		
		@Override
		public Pageable previousOrFirst() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Pageable next() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public boolean hasPrevious() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public Sort getSort() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public int getPageSize() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public int getPageNumber() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public int getOffset() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public Pageable first() {
			// TODO Auto-generated method stub
			return null;
		}
	};

	@Configuration
	static class ContextConfiguration {
		@Bean
		ProductService productService() { return new ProductServiceImpl(); }

		@Bean
		AppConstantConfig appConfig() { return new AppConstantConfig(); }
	}

	@Before
	public void before() {
		product = new Product();
		product.setCategory("category");
		product.setCode("code");
		product.setCreatedAt(12345);
		product.setDescription("Description");
		product.setId("id");
		product.setImageDefault("imageDefault");
		List<String> imageList = new ArrayList<String>();
		imageList.add("link1");
		imageList.add("link2");
		product.setImageList(imageList);
		product.setIsBuyable((byte) 1);
		product.setIsDeleted((byte) 0);
		product.setIsDisplayable((byte) 1);
		product.setIsRecommended((byte) 1);
		product.setName("name");
		product.setPrice(new BigDecimal(1234));
		product.setQuantity(23);
		product.setSku("sku");
		product.setUpdatedAt(12345);
		
		product.getCategory();
		product.getCode();
		product.getCreatedAt();
		product.getDescription();
		product.getId();
		product.getImageDefault();
		product.getImageList();
		product.getIsBuyable();
		product.getIsDeleted();
		product.getIsDisplayable();
		product.getIsRecommended();
		product.getName();
		product.getPrice();
		product.getQuantity();
		product.getSku();
		product.getUpdatedAt();
		List<Product> productList = new ArrayList<Product>();
		productList.add(product);
		
		List<ProductDTO> productDtoList = new ArrayList<ProductDTO>();
		productDtoList.add(ETDUtils.toProductDto(product));
		try {
			Mockito.when(productRepository.findWithLimit(Mockito.anyInt(),Mockito.anyInt())).thenReturn(productList);
		} catch (Exception e) {}
		Mockito.when(productRepository.getProductByCategory(Mockito.any(String.class), Mockito.any(Pageable.class)))
				.thenReturn(productList);
	//	Mockito.when(productRepository.findById(Mockito.any(String.class))).thenReturn(product);
		Mockito.when(productRepository.getProductByCodeAndName(Mockito.any(String.class),
				Mockito.any(String.class))).thenReturn(product);
		Mockito.when(productRepository.findBySearchStrContaining(Mockito.anyString())).thenReturn(productList);
	}
	
	@Test
	public void getAllByPageTest() throws Exception {
		List<ProductDTO> productDtoList = new ArrayList<ProductDTO>();
		productDtoList.add(ETDUtils.toProductDto(product));
		List<ProductDTO> temp = productService.getAllByPage(page);
		assertEquals(productDtoList.get(0).getSku(), temp.get(0).getSku());
	}
	
	@Test(expected=Exception.class)
	public void shouldThrowExceptionWhenErrorOccurs() throws Exception {
		Mockito.when(productRepository.findWithLimit(Mockito.anyInt(),Mockito.anyInt()	)).thenThrow(new Exception());
		productService.getAllByPage(page);
	}
	
	@Test
	public void getProductByCategoryTest() {
		ProductDTO productDto = ETDUtils.toProductDto(product);
		Page<ProductDTO> temp = productService.getProductByCategory("",page);
		assertEquals(productDto.getCategoryName(), temp.getContent().get(0).getCategoryName());
	}
	
	@Test
	public void getProductByIdTest() {
		ProductDTO productDto = ETDUtils.toProductDto(product);
	//	ProductDTO temp = productService.getProductById(Mockito.anyString(), Optional.empty());
		//assertEquals(productDto.getId(), temp.getId());
	}
	
	@Test
	public void findByUniqueIdentifierTest() {
		/* This method sets the appConfig.getProductTokenizer property in AppConstantConfig to be used in test
		As without Spring Context appconstant.properties are not injected in AppConstantConfig properties */
		ReflectionTestUtils.setField(appConfig, "PRODUCT_ID_TOKINIZER", "-");

		ProductDTO productDto = ETDUtils.toProductDto(product);
		ProductDTO temp = productService.findByUniqueIdentifier("");
		assertNull(temp);
		temp = productService.findByUniqueIdentifier("code-name");
		assertEquals(productDto.getItem().getDefaultImage(), temp.getItem().getDefaultImage());
	}
	
	
	@Test
	public void searchProductByNameWithResultsTest(){		
		List<ProductDTO> productDtoList = new ArrayList<ProductDTO>();
		productDtoList.add(ETDUtils.toProductDto(product));
		List<ProductDTO> temp = productService.searchProductByName(Mockito.anyString());
		assertEquals(productDtoList.get(0).getItem().getName(), temp.get(0).getItem().getName());		
	}
	
	@Test(expected=RestException.class)
	public void searchProductByNameWithNoResultsTest(){		
		Mockito.when(productRepository.findBySearchStrContaining(Mockito.anyString())).thenThrow(new RestException(HttpStatus.NO_CONTENT, "No Product Found with given Name", ErrorLevel.INFO));		
		 productService.searchProductByName(Mockito.anyString());		
	}
	
	
	
	
}
