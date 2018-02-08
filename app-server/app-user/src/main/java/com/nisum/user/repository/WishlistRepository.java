package com.nisum.user.repository;

import com.nisum.user.model.Wishlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


// TODO: Auto-generated Javadoc
/**
 * The Interface WishlistRepository.
 *
 * @author omkhan
 */
public interface WishlistRepository extends JpaRepository<Wishlist, Long>{

	/**
	 * Find wishlist by id.
	 *
	 * @param id the id
	 * @return the wishlist
	 */
	@Query("SELECT distinct w FROM Wishlist w WHERE w.id = ?1")
	public Wishlist findWishlistById(Long id);
	
	/**
	 * Find wishlist by user id.
	 *
	 * @param userId the user id
	 * @return the wishlist
	 */
	@Query("SELECT distinct w FROM Wishlist w JOIN w.user u WHERE u.id = ?1 AND (w.isDeleted = 0 OR w.isDeleted = NULL)")
	public List<Wishlist> findWishlistByUserId(Long userId);

	@Query("SELECT distinct w FROM Wishlist w JOIN w.user u WHERE u.id = ?1 AND (w.isDeleted = 0 OR w.isDeleted = NULL) AND w.isDefault = 1")
	public List<Wishlist> findDefaultWishlist(Long userId);

	@Query("SELECT distinct w FROM Wishlist w JOIN w.user u WHERE u.id = ?1 AND (w.isDeleted = 0 OR w.isDeleted = NULL)")
	public Page<Wishlist> findWishlistByUserId(Long userId, Pageable pageable);
}
