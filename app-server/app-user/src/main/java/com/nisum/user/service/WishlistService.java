package com.nisum.user.service;

import com.nisum.user.dto.WishlistDTO;
import com.nisum.user.dto.WishlistResponseDTO;
import com.nisum.user.model.Wishlist;
import com.nisum.user.model.WishlistItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface WishlistService {
	
	
	
	Wishlist saveWishlist(WishlistDTO wishlistDTO);
	Boolean deleteWishlist(Wishlist wishlist);
	Boolean deleteWishlistById(Long id);
	Boolean makeWishlistDefault(Wishlist wishlist);
	WishlistItem addToWishlist(Long userId, Long wishlistId, String itemId);
	Boolean removeFromWishList(Long wishlistItemId);
	
	Wishlist getWishlistById(Long id);
	List<Wishlist> getWishlistByUserId(Long userId);
	
	WishlistItem getWishlistItemById(Long id);
	Set<WishlistItem> getWishlistItemsByWishlistId(Long wishlistId);
	Wishlist getDefaultWishlist(Long userId);
	Boolean isItemInWishlist(Long userId, Long itemId);
	
	Page<Wishlist> getWishlistByUserId(Long userid, Pageable pageable);
	
	List<WishlistResponseDTO> getWishlistByUserIdWithResponseDTO(Long userid, Pageable pageable);
	
	WishlistItem removeFromWishlist(Long wishlistid, String itemid);
	
}
