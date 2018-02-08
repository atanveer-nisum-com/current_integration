package com.nisum.user.test.service;

import com.nisum.user.model.User;
import com.nisum.user.model.Wishlist;
import com.nisum.user.model.WishlistItem;
import com.nisum.user.repository.WishlistItemRepository;
import com.nisum.user.repository.WishlistRepository;
import com.nisum.user.service.UserService;
import com.nisum.user.service.WishlistService;
import com.nisum.user.test.mock.models.MockModelFactory;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class WishListServiceTest extends BaseServiceTest {

	@Autowired
	private WishlistRepository wishlistRepository;

	@Autowired
	private WishlistService wishlistService;

	@Autowired
	private UserService userService;

	@Autowired
	private WishlistItemRepository wishlistItemRepository;

	@Test
	public void shouldGetWishlistById() {
		Wishlist wishList = MockModelFactory.getMockWishlist();
		assertNotNull(wishList);
		
		Mockito.when(wishlistRepository.findWishlistById(Mockito.anyLong())).thenReturn(wishList);
		assertEquals(wishList, wishlistService.getWishlistById(Matchers.anyLong()));
		
	}
	
	@Test
	public void shouldGetWishlistByUserId() {
		List<Wishlist> wishList = MockModelFactory.getMockPageWishlist();
		assertNotNull(wishList);
		
		Mockito.when(wishlistRepository.findWishlistByUserId(Mockito.anyLong())).thenReturn(wishList);
		assertEquals(wishList, wishlistService.getWishlistByUserId(Matchers.anyLong()));
		
	}
	
	@Test
	public void shouldSaveWishlist() {
		Wishlist wishList = MockModelFactory.getMockWishlistDTO().toWishlist();
		List<Wishlist> wishLists = MockModelFactory.getMockPageWishlist();
		assertNotNull(wishList);
		
		Mockito.when(wishlistRepository.save(Mockito.any(Wishlist.class))).thenReturn(wishList);
		Mockito.when(wishlistRepository.findWishlistByUserId(Matchers.anyLong())).thenReturn(wishLists);
		assertEquals(wishList, wishlistService.saveWishlist(MockModelFactory.getMockWishlistDTO()));
		
		assertTrue(wishList.getIsDefault());
	}
	
	@Test
	public void shouldNotSaveWishlist() {
		Wishlist wishList = MockModelFactory.getNullMockWishlistDTO().toWishlist();
		List<Wishlist> wishLists = MockModelFactory.getMockPageWishlist();
		
		Mockito.when(wishlistRepository.save(Mockito.any(Wishlist.class))).thenReturn(wishList);
		Mockito.when(wishlistRepository.findWishlistByUserId(Matchers.anyLong())).thenReturn(wishLists);
		
		assertEquals(wishList, wishlistService.saveWishlist(MockModelFactory.getNullMockWishlistDTO()));
		
	}
	
	@Test
	public void shouldAddItemToWishlist() {

		Wishlist wishList = MockModelFactory.getMockWishlist();
		wishList.setWishlistItems(new HashSet<WishlistItem>());
		
		WishlistItem wishlistItem = MockModelFactory.getMockWishlistItems();
		List<Wishlist> wishLists = MockModelFactory.getMockPageWishlist();
		
		
		Mockito.when(wishlistRepository.save(Mockito.any(Wishlist.class))).thenReturn(wishList);
		Mockito.when(wishlistRepository.findWishlistById(Mockito.anyLong())).thenReturn(wishList);
		Mockito.when(wishlistRepository.findDefaultWishlist(Matchers.anyLong())).thenReturn(wishLists);

		assertEquals(wishlistItem, wishlistService.addToWishlist(1l, 1l, wishlistItem.getItemUUID()));
		
		
		
	}
	
	@Test
	public void shouldAddItemToWishlistWithWishListIdNull() {

		Wishlist wishList = MockModelFactory.getMockWishlist();
		User user = MockModelFactory.getMockUser();
		
		WishlistItem wishlistItem = MockModelFactory.getMockWishlistItems();
		List<Wishlist> wishLists = new ArrayList<Wishlist>();
		
		wishList.getWishlistItems().add(wishlistItem);
		wishLists.add(wishList);
		
		Mockito.when(wishlistRepository.save(Mockito.any(Wishlist.class))).thenReturn(wishList);
		Mockito.when(wishlistRepository.findWishlistById(Mockito.anyLong())).thenReturn(wishList);
		Mockito.when(wishlistRepository.findDefaultWishlist(Matchers.anyLong())).thenReturn(wishLists);

		Mockito.when(userService.findOne(Mockito.anyLong())).thenReturn(user);
		
		assertEquals(wishlistItem, wishlistService.addToWishlist(user.getId(), null, wishlistItem.getItemUUID()));
		
	}
	
	@Test
	public void shouldAddItemToWishlistWithWishListIdZero() {

		Wishlist wishList = MockModelFactory.getMockWishlist();
		
		User user = MockModelFactory.getMockUser();
		
		WishlistItem wishlistItem = MockModelFactory.getMockWishlistItems();
		List<Wishlist> wishLists = MockModelFactory.getMockPageWishlist();
		
		Mockito.when(wishlistRepository.save(Mockito.any(Wishlist.class))).thenReturn(wishList);
		Mockito.when(wishlistRepository.findWishlistById(Mockito.anyLong())).thenReturn(wishList);
		Mockito.when(wishlistRepository.findDefaultWishlist(Matchers.anyLong())).thenReturn(wishLists);
		Mockito.when(userService.findOne(Mockito.anyLong())).thenReturn(user);
		
		assertEquals(wishlistItem, wishlistService.addToWishlist(user.getId(), 0l, wishlistItem.getItemUUID()));
		
	}
	
	@Test
	public void shouldDeleteWishlist() {

		Wishlist wishList = MockModelFactory.getMockWishlist();
		
		assertEquals(true, wishlistService.deleteWishlist(wishList));
		
	}
	
	@Test
	public void shouldDeleteWishlistById() {

		assertEquals(true, wishlistService.deleteWishlistById(Mockito.anyLong()));
		
	}
	
	@Test
	public void shouldGetWishlistItemById() {

		WishlistItem wishListItem = MockModelFactory.getMockWishlistItems();
		
		Mockito.when(wishlistItemRepository.findWishlistItemById(Matchers.anyLong())).thenReturn(wishListItem);
		
		assertEquals(wishListItem, wishlistService.getWishlistItemById(Mockito.anyLong()));
		
	}
	
	@Test
	public void shouldGetWishlistItemsByWishlistId() {

		Set<WishlistItem> setWishListItems = new HashSet<WishlistItem>();
		
		Mockito.when(wishlistItemRepository.findWishlistItemByWishlistId(Matchers.anyLong())).thenReturn(setWishListItems);
		
		assertEquals(setWishListItems, wishlistService.getWishlistItemsByWishlistId(Mockito.anyLong()));
		
	}
	
	@Test
	public void shouldRemoveFromWishlist() {

		assertEquals(true, wishlistService.removeFromWishList(Mockito.anyLong()));
		
	}
	
	@Test
	public void shouldRemoveFromWishlist2() {

		WishlistItem wishListItem = MockModelFactory.getMockWishlistItems();
		
		Mockito.when(wishlistItemRepository.findWishlistItemByUUID(Matchers.anyString())).thenReturn(wishListItem);
		
		assertEquals(wishListItem, wishlistService.removeFromWishlist(Matchers.anyLong(),wishListItem.getItemUUID()));
		
	}
	
	@Test
	public void shouldIsItemInWishlist() {

		assertEquals(false, wishlistService.isItemInWishlist(1l,2l));
		
	}
	

}
