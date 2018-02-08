package com.nisum.user.test.controller;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.user.controller.WishlistController;
import com.nisum.user.dto.WishlistDTO;
import com.nisum.user.dto.WishlistDefaultNameDTO;
import com.nisum.user.dto.WishlistResponseDTO;
import com.nisum.user.model.Wishlist;
import com.nisum.user.model.WishlistItem;
import com.nisum.user.service.WishlistService;

@WebMvcTest(value = WishlistController.class, secure = false)

public class WishlistControllerTest extends BaseControllerTest {
	
	@MockBean
	WishlistService wishlistService;
	
	private final String WISHLIST_URL = "/users/wishlists";
	private final String WISHLIST_UPDATE_URL = "/users/wishlists/1";
	private final String WISHLIST_ITEM_URL = "/users/wishlists/1/items/testid";
	
	@Test
	public void getUsersWishlistsShouldReturnWishLists() throws Exception {
		WishlistResponseDTO wishlistResponseDTO = new WishlistResponseDTO();
		
		wishlistResponseDTO.setId(1L);
		wishlistResponseDTO.setName("Shirts");
		
		List<WishlistResponseDTO> wishlists = new ArrayList<WishlistResponseDTO>();
		wishlists.add(wishlistResponseDTO);
		//Page<WishlistResponseDTO> content = new PageImpl<>(wishlists);
		
		
		Mockito.when(wishlistService.getWishlistByUserIdWithResponseDTO(Mockito.anyLong(),Mockito.any(Pageable.class))).thenReturn(wishlists);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(WISHLIST_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.header("userId", "3");
		
		mvc.perform(requestBuilder)
				.andExpect(status().isOk())
		        .andExpect(jsonPath("$.content").isNotEmpty());
		
		
		
	}
	@Test
	public void createWishlistShouldReturnWishlist() throws JsonProcessingException,Exception {
		
		Wishlist wishlist = new Wishlist();
		wishlist.setName("testlist");
		wishlist.setIsDefault(false);
		WishlistDefaultNameDTO wishlistDefaultNameDTO = new WishlistDefaultNameDTO();
		wishlistDefaultNameDTO.setIsDefault(false);
		wishlistDefaultNameDTO.setName("testlist");
		String content = new ObjectMapper()
		        .writeValueAsString(wishlistDefaultNameDTO);
		
		Mockito.when(wishlistService.saveWishlist(Mockito.any(WishlistDTO.class))).thenReturn(wishlist);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(WISHLIST_URL)
				.content(content)
				.contentType(MediaType.APPLICATION_JSON)
				.header("userId", "3");
		
		mvc.perform(requestBuilder)
				.andExpect(status().isOk())
		        .andExpect(jsonPath("$.name").value("testlist"));
		
		
	}
	@Test
	public void updateWishlistShouldReturnWishlist() throws JsonProcessingException,Exception {
		Wishlist wishlist = new Wishlist();
		wishlist.setName("testlist");
		wishlist.setIsDefault(false);
		WishlistDefaultNameDTO wishlistDefaultNameDTO = new WishlistDefaultNameDTO();
		wishlistDefaultNameDTO.setIsDefault(false);
		wishlistDefaultNameDTO.setName("testlist");
		String content = new ObjectMapper()
		        .writeValueAsString(wishlistDefaultNameDTO);
		
		Mockito.when(wishlistService.saveWishlist(Mockito.any(WishlistDTO.class))).thenReturn(wishlist);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put(WISHLIST_UPDATE_URL)
				.content(content)
				.contentType(MediaType.APPLICATION_JSON)
				.header("userId", "3");
		
		mvc.perform(requestBuilder)
				.andExpect(status().isOk())
		        .andExpect(jsonPath("$.name").value("testlist"));
		
	}
	@Test
	public void deleteWishlistShouldReturnWishlist() throws Exception {
		Wishlist wishlist = new Wishlist();
		wishlist.setId(1l);
		wishlist.setName("testlist");
		wishlist.setIsDefault(false);
		
		Mockito.when(wishlistService.deleteWishlistById(Mockito.anyLong())).thenReturn(true);
		Mockito.when(wishlistService.getWishlistById(Mockito.anyLong())).thenReturn(wishlist);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete(WISHLIST_UPDATE_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.header("userId", "3");
		
		
		mvc.perform(requestBuilder)
				.andExpect(status().isOk())
		        .andExpect(jsonPath("$.id").value("1"));
		
	}
	@Test
	public void addToWishlistShouldReturnProduct() throws Exception {
		WishlistItem wishlistItem = new WishlistItem();
		wishlistItem.setItemUUID("testid");
		wishlistItem.setId(1L);		
		Mockito.when(wishlistService.addToWishlist(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyString())).thenReturn(wishlistItem);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(WISHLIST_ITEM_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.header("userId", "3");
		
		
		mvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value("1"));
		
	}
	@Test
	public void removeFromWishListShouldReturnProduct() throws Exception {
		WishlistItem wishlistItem = new WishlistItem();
		wishlistItem.setId(1L);
		wishlistItem.setItemUUID("testid");
		
		
		Mockito.when(wishlistService.removeFromWishlist(Mockito.anyLong(), Mockito.anyString())).thenReturn(wishlistItem);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete(WISHLIST_ITEM_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.header("userId", "3");
		
		
		mvc.perform(requestBuilder)
				.andExpect(status().isOk())
		        .andExpect(jsonPath("$.id").value("1"));
		
	}
	

}
