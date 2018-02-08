
package com.nisum.user.dto;

import com.nisum.common.util.BaseDTO;
import com.nisum.user.model.User;
import com.nisum.user.model.Wishlist;
import com.nisum.user.model.WishlistItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WishlistDTO extends BaseDTO implements Serializable {
	
	private static final long serialVersionUID = 1298379182371289710L;
	private Long userId;
	private Long wishlistId;
	private String name;
	private Boolean isDefault;
	private String itemUUID;
	
	
	
	private List<String> itemUUIDList = new ArrayList<String>();
	
	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}


	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	
	/**
	 * @return the itemUUID
	 */
	public String getItemUUID() {
		return itemUUID;
	}


	/**
	 * @param itemUUID the itemUUID to set
	 */
	public void setItemUUID(String itemUUID) {
		this.itemUUID = itemUUID;
	}


	/**
	 * @return the itemUUIDList
	 */
	public List<String> getItemUUIDList() {
		return itemUUIDList;
	}


	/**
	 * @param itemUUIDList the itemUUIDList to set
	 */
	public void setItemUUIDList(List<String> itemUUIDList) {
		this.itemUUIDList = itemUUIDList;
	}


	/**
	 * @return the wishlistId
	 */
	public Long getWishlistId() {
		return wishlistId;
	}


	/**
	 * @param wishlistId the wishlistId to set
	 */
	public void setWishlistId(Long wishlistId) {
		this.wishlistId = wishlistId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the isDefault
	 */
	public Boolean getIsDefault() {
		return isDefault;
	}


	/**
	 * @param isDefault the isDefault to set
	 */
	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}



	

	public Wishlist toWishlist() {
		
		Wishlist wishlist = new Wishlist();
		User user = new User();
		user.setId(this.getUserId());
		
		wishlist.setId(this.getWishlistId());
		wishlist.setIsDefault(this.getIsDefault());
		wishlist.setName(this.getName());
		wishlist.setUser(user);
		wishlist.setWishlistItems(getWishlistItems(wishlist));
		
		
		
		return wishlist;
		
	}
	
	private Set<WishlistItem> getWishlistItems(Wishlist wishlist){
		
		WishlistItem wishlistItem;
		
		Set<WishlistItem> wislistItems = new HashSet<WishlistItem>();
		for(String itemUUId : this.getItemUUIDList()) {
			
			wishlistItem = new WishlistItem();
			
			wishlistItem.setItemUUID(itemUUId);
			wishlistItem.setWishlist(wishlist);
			wishlistItem.setIsDeleted(false);
			
		
			
			wislistItems.add(wishlistItem);
			
		}
		
		return wislistItems;
	}

}
