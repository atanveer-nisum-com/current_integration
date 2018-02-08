package com.nisum.user.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the wishlist database table.
 *
 */
@Entity
@NamedQuery(name="Wishlist.findAll", query="SELECT w FROM Wishlist w")
@SQLDelete(sql="Update Item i SET i.isDeleted = 1 where i.id=?")
@Where(clause="is_deleted = 0" )
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","user"})
public class Wishlist implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private Long id;

	/** The created at. */
	private Long createdAt;

	/** The is default. */
	private Boolean isDefault;

	/** The is deleted. */
	private Boolean isDeleted;

	/** The name. */
	private String name;

	/** The updated at. */
	private Long updatedAt;

	/** The wishlist items. */
	private Set<WishlistItem> wishlistItems;

	/** The user. */
	private User user;

	/**
	 * Instantiates a new wishlist.
	 */
	public Wishlist() {
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
	 * Gets the created at.
	 *
	 * @return the created at
	 */
	@Column(name="created_at")
	public Long getCreatedAt() {
		return this.createdAt;
	}

	/**
	 * Sets the created at.
	 *
	 * @param createdAt the new created at
	 */
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}


	/**
	 * Gets the checks if is default.
	 *
	 * @return the checks if is default
	 */
	@Column(name="is_default")
	public Boolean getIsDefault() {
		return this.isDefault;
	}

	/**
	 * Sets the checks if is default.
	 *
	 * @param isDefault the new checks if is default
	 */
	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * Gets the updated at.
	 *
	 * @return the updated at
	 */
	@Column(name="updated_at")
	public Long getUpdatedAt() {
		return this.updatedAt;
	}

	/**
	 * Sets the updated at.
	 *
	 * @param updatedAt the new updated at
	 */
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}


	/**
	 * Gets the wishlist items.
	 *
	 * @return the wishlist items
	 */
	//bi-directional many-to-one association to WishlistItem
	@OneToMany(fetch=FetchType.EAGER, mappedBy="wishlist", cascade=CascadeType.ALL,orphanRemoval = true)
	public Set<WishlistItem> getWishlistItems() {
		return this.wishlistItems;
	}

	/**
	 * Sets the wishlist items.
	 *
	 * @param wishlistItems the new wishlist items
	 */
	public void setWishlistItems(Set<WishlistItem> wishlistItems) {
		this.wishlistItems = wishlistItems;
	}

	/**
	 * Adds the wishlist item.
	 *
	 * @param wishlistItem the wishlist item
	 * @return the wishlist item
	 */
	public WishlistItem addWishlistItem(WishlistItem wishlistItem) {
		getWishlistItems().add(wishlistItem);
		wishlistItem.setWishlist(this);

		return wishlistItem;
	}

	/**
	 * Removes the wishlist item.
	 *
	 * @param wishlistItem the wishlist item
	 * @return the wishlist item
	 */
	public WishlistItem removeWishlistItem(WishlistItem wishlistItem) {
		getWishlistItems().remove(wishlistItem);
		wishlistItem.setWishlist(null);

		return wishlistItem;
	}


	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	public User getUser() {
		return this.user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}

}