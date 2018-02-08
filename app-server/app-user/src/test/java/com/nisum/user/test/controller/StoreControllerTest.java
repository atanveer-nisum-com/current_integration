package com.nisum.user.test.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.user.controller.StoreController;
import com.nisum.user.dto.StoreDTO;
import com.nisum.user.service.StoreService;

@WebMvcTest(value = StoreController.class, secure = false)
public class StoreControllerTest extends BaseControllerTest {

	@MockBean
	private StoreService storeService;

	private final String STORES_URL = "/stores";
	private final String STORE_URL = "/stores/1";
	
	@Test
	public void getStoresShouldReturnListofStores() throws Exception  {
		List<StoreDTO> storesList = new ArrayList<StoreDTO>();
		StoreDTO storeDTO = new StoreDTO();
		storeDTO.setName("Macy's Mission Valley Home");
		storeDTO.setZipcode(92108l);
		storeDTO.setAddress("1555 Camino de La Reina San Diego, CA");
		storeDTO.setId(1l);
		storesList.add(storeDTO);
		Page<StoreDTO> stores = new PageImpl<>(storesList);
		
		Mockito.when(storeService.getStores()).thenReturn(stores);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(STORES_URL)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		mvc.perform(asyncDispatch(result))
				.andExpect(status().isOk())
		        .andExpect(jsonPath("$.content[0].id").value("1"));
	}
	@Test
	public void getStoreByIdShouldReturnStore() throws Exception {
	
		StoreDTO storeDTO = new StoreDTO();
		storeDTO.setName("Macy's Mission Valley Home");
		storeDTO.setZipcode(92108l);
		storeDTO.setAddress("1555 Camino de La Reina San Diego, CA");
		storeDTO.setId(1l);
		
		Mockito.when(storeService.getStoreById(Mockito.anyLong())).thenReturn(storeDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(STORE_URL)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		mvc.perform(asyncDispatch(result))
				.andExpect(status().isOk())
		        .andExpect(jsonPath("$.id").value("1"));
		
	}
	@Test
	public void getStoreByZipcodeShouldReturnStores() throws Exception {
		List<StoreDTO> storesList = new ArrayList<StoreDTO>();
		StoreDTO storeDTO = new StoreDTO();
		storeDTO.setName("Macy's Mission Valley Home");
		storeDTO.setZipcode(92108l);
		storeDTO.setAddress("1555 Camino de La Reina San Diego, CA");
		storeDTO.setId(1l);
		storesList.add(storeDTO);
		Page<StoreDTO> stores = new PageImpl<>(storesList);
		
		Mockito.when(storeService.getStoreByZipcode(Mockito.anyLong(), Mockito.any(Pageable.class))).thenReturn(stores);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(STORES_URL)
				.param("zipcode", "92108")
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		mvc.perform(asyncDispatch(result))
				.andExpect(status().isOk())
		        .andExpect(jsonPath("$.content[0].id").value("1"));
		
	}
	@Test
	public void saveStoreShouldReturnStore() throws Exception {
		
		StoreDTO storeDTO = new StoreDTO();
		Map<String, Long> storeId = new HashMap<>();
		storeId.put("storeId", 1L);
		storeDTO.setName("Macy's Mission Valley Home");
		storeDTO.setZipcode(92108l);
		storeDTO.setAddress("1555 Camino de La Reina San Diego, CA");
		storeDTO.setId(1l);
		String content = new ObjectMapper()
        .writeValueAsString(storeId);
		
		Mockito.when(storeService.saveStore(Mockito.anyLong(),Mockito.anyLong())).thenReturn(storeDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(STORES_URL)
				.content(content)
				.contentType(MediaType.APPLICATION_JSON)
				.header("userId", "1");
		
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		mvc.perform(asyncDispatch(result))
				.andExpect(status().isOk())
		        .andExpect(jsonPath("$.id").value("1"));
		
	}
	@Test
	public void getStoreByUserShouldReturnStore() throws Exception {
		
		StoreDTO storeDTO = new StoreDTO();
		storeDTO.setName("Macy's Mission Valley Home");
		storeDTO.setZipcode(92108l);
		storeDTO.setAddress("1555 Camino de La Reina San Diego, CA");
		storeDTO.setId(1L);
		
		Mockito.when(storeService.getStoreByUserId(Mockito.anyLong())).thenReturn(storeDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(STORES_URL)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.header("userId", "2");

		
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		this.mvc.perform(asyncDispatch(result))
		 .andExpect(status().isOk());
	        
	}
	
	

}
