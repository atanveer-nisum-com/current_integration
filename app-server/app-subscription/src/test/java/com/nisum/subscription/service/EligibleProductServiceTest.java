package com.nisum.subscription.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import com.nisum.subscription.dto.EligibleProductDTO;
import com.nisum.subscription.model.EligibleProduct;
import com.nisum.subscription.repository.EligibleProductRepository;
import com.nisum.subscription.util.Converter;

public class EligibleProductServiceTest {
	@Autowired
	private EligibleProductService productService;
	@MockBean
	private EligibleProductRepository productRepository;
	
	private EligibleProduct product;
	
	Pageable page = new Pageable() {

		@Override
		public Pageable first() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getOffset() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int getPageNumber() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int getPageSize() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Sort getSort() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean hasPrevious() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Pageable next() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Pageable previousOrFirst() {
			// TODO Auto-generated method stub
			return null;
		}
		
	};
	
	@Before
	public void before() {
		product = new EligibleProduct();
		product.setProductID("productID");
		product.setCategory("category");
		product.setClientID("clientID");
		product.setDelCharge(0.00);
		product.setDelFrequency("delFrequency");
		product.setDescription("description");
		product.setName("name");
		product.setPrice(new BigDecimal(1234));
		product.setCampaignID("10");
		
		product.getCategory();
		product.getClientID();
		product.getDelCharge();
		product.getDelFrequency();
		product.getDescription();
		product.getName();
		product.getPrice();
		product.getProductID();
		product.getCampaignID();
		
		
		List<EligibleProduct> productList = new ArrayList<EligibleProduct>();
		productList.add(product);
		
		List<EligibleProductDTO> productDtoList = new ArrayList<EligibleProductDTO>();
		productDtoList.add(Converter.toEligibleProductDto(product));
		Mockito.when(productRepository.findByProductIDAndClientID(Mockito.any(String.class),
				Mockito.any(String.class))).thenReturn(product);
		
	}
	
	/**
	 * TestCase 1 : To get the product by clientID and productID.
	 *@return productID
	 */
	
	@Test
	public void getProductByIDAndClientIDTest() throws Exception {
		EligibleProductDTO productDto = Converter.toEligibleProductDto(product);
		EligibleProductDTO temp = productService.getProductByIDAndClientID("", "");
		assertNull(temp);
		temp = productService.getProductByIDAndClientID("productID","clientID");
		assertEquals(productDto.getProductID(), temp.getProductID());
	}
	
	/**
	 * TestCase 2 : To get the product list by clientID
	 *@return productDtoList
	 */
	@Test
	public void getProductByClientIDTest() throws Exception {
		List<EligibleProductDTO> productDtoList = new ArrayList<EligibleProductDTO>();
		productDtoList.add(Converter.toEligibleProductDto(product));
		
		List<EligibleProductDTO> productDtoListTemp = productService.getProductByClientID("clientID", page);
		
		assertEquals(productDtoList, productDtoListTemp);
	}
	
	/**
	 * TestCase 3 : To get the product by clientID and campaignID.
	 *@return productID
	 */
	
	@Test
	public void getProductByCampaignIDAndClientIDTest() throws Exception {
		EligibleProductDTO productDto = Converter.toEligibleProductDto(product);
		EligibleProductDTO temp = productService.getProductByCampaignIDAndClientID("", "");
		assertNull(temp);
		temp = productService.getProductByCampaignIDAndClientID("10","clientID");
		assertEquals(productDto.getProductID(), temp.getProductID());
	}
	
	/**
	 * TestCase 4 : To get the product list by category.
	 *@return productDtoList
	 */
	
	@Test
	public void getProductByCategoryTest() throws Exception {
		List<EligibleProductDTO> productDtoList = new ArrayList<EligibleProductDTO>();
		productDtoList.add(Converter.toEligibleProductDto(product));
		
		List<EligibleProductDTO> productDtoListTemp = productService.getProductsByCategory("category", page);
		
		assertEquals(productDtoList, productDtoListTemp);
	}
	
	/**
	 * TestCase 5 : To get the product list by deliveryfrequency.
	 *@return productDtoList
	 */
	@Test
	public void getProductsByDeliveryFrequencyTest() throws Exception {
		List<EligibleProductDTO> productDtoList = new ArrayList<EligibleProductDTO>();
		productDtoList.add(Converter.toEligibleProductDto(product));
		
		List<EligibleProductDTO> productDtoListTemp = productService.getProductsByDeliveryFrequency("delFrequency", page);
		
		assertEquals(productDtoList, productDtoListTemp);
	}

}
