package com.nisum.user.repository;

import com.nisum.user.model.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * The Interface WishlistItemRepository.
 */
public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long>{
	
	
	/**
	 * Find wishlist item by id.
	 *
	 * @param id the id
	 * @return the wishlist item
	 */
	@Query("SELECT distinct w FROM WishlistItem w WHERE w.id = ?1")
	public WishlistItem findWishlistItemById(Long id);
	
	/**
	 * Find wishlist item by wishlist id.
	 *
	 * @param id the id
	 * @return the sets the
	 */
	@Query("SELECT distinct w FROM WishlistItem w JOIN w.wishlist wl WHERE wl.id = ?1")
	public Set<WishlistItem> findWishlistItemByWishlistId(Long id);
	
	
	@Query("SELECT distinct w FROM WishlistItem w WHERE w.itemUUID = ?1")
	public WishlistItem findWishlistItemByUUID(String id);
	

}
