package com.nisum.user.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the wishlist_items database table.
 *
 */
@Entity
@Table(name="wishlist_items")
@NamedQuery(name="WishlistItem.findAll", query="SELECT w FROM WishlistItem w")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","wishlist"})
public class WishlistItem implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private Long id;

	/** The is deleted. */
	private Boolean isDeleted;

	/** The wishlist. */
	private Wishlist wishlist;


	/** The Item UUID */
	private String itemUUID;


	/**
	 * Instantiates a new wishlist item.
	 */
	public WishlistItem() {
	}


	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * Gets the checks if is deleted.
	 *
	 * @return the checks if is deleted
	 */
	@Column(name="is_deleted")
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}

	/**
	 * Sets the checks if is deleted.
	 *
	 * @param isDeleted the new checks if is deleted
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}


	/**
	 * Gets the wishlist.
	 *
	 * @return the wishlist
	 */
	//bi-directional many-to-one association to Wishlist
	@ManyToOne(fetch=FetchType.LAZY)
	public Wishlist getWishlist() {
		return this.wishlist;
	}

	/**
	 * Sets the wishlist.
	 *
	 * @param wishlist the new wishlist
	 */
	public void setWishlist(Wishlist wishlist) {
		this.wishlist = wishlist;
	}




	@Column(name="item_uuid")
	public String getItemUUID() {
		return itemUUID;
	}


	public void setItemUUID(String itemUUID) {
		this.itemUUID = itemUUID;
	}




	@Override
	public int hashCode() {
		//return Integer.parseInt((this.getItem().getId()+this.getWishlist().getId())+"");
		return Integer.parseInt(this.getItemUUID().hashCode()+"" );
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		WishlistItem wishlistItem = (WishlistItem) obj;

		if (null != this.getItemUUID() ) {
			return this.getItemUUID().equals(wishlistItem.getItemUUID()) ? true : false;
		}
		return false;

	}


}