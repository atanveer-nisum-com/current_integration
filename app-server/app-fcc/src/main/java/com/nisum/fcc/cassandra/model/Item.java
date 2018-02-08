package com.nisum.fcc.cassandra.model;

import com.nisum.common.util.BaseEntity;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the items database table.
 * 
 */
@Table(value = "item")
public class Item extends BaseEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4808351310314935798L;

	@PrimaryKey
	private ItemKey itemKey;

	/** The description. */
	@Column(value = "description")
	private String description;

	/** The quantity. */
	@Column(value = "quantity")
	private Integer quantity;

	/** The is buyable. */
	@Column(value = "buyable")
	private Byte isBuyable;

	/** The is displayable. */
	@Column(value = "displayable")
	private Byte isDisplayable;

	/** The created at. */
	@Column(value = "created_at")
	private Integer createdAt;

	/** The updated at. */
	@Column(value = "updated_at")
	private Integer updatedAt;

	@Column(value = "image_default")
	private String imageDefault;

	/** The image List. */
	@Column(value = "images_list")
	private List<String> imageList;

	/** The price. */
	@Column(value = "price")
	private BigDecimal price;

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description
	 *            the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity.
	 *
	 * @param quantity
	 *            the new quantity
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * Gets the checks if is buyable.
	 *
	 * @return the checks if is buyable
	 */
	public Byte getIsBuyable() {
		return isBuyable;
	}

	/**
	 * Sets the checks if is buyable.
	 *
	 * @param isBuyable
	 *            the new checks if is buyable
	 */
	public void setIsBuyable(Byte isBuyable) {
		this.isBuyable = isBuyable;
	}

	/**
	 * Gets the checks if is displayable.
	 *
	 * @return the checks if is displayable
	 */
	public Byte getIsDisplayable() {
		return isDisplayable;
	}

	/**
	 * Sets the checks if is displayable.
	 *
	 * @param isDisplayable
	 *            the new checks if is displayable
	 */
	public void setIsDisplayable(Byte isDisplayable) {
		this.isDisplayable = isDisplayable;
	}

	/**
	 * Gets the created at.
	 *
	 * @return the created at
	 */
	public Integer getCreatedAt() {
		return createdAt;
	}

	/**
	 * Sets the created at.
	 *
	 * @param createdAt
	 *            the new created at
	 */
	public void setCreatedAt(Integer createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * Gets the updated at.
	 *
	 * @return the updated at
	 */
	public Integer getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * Sets the updated at.
	 *
	 * @param updatedAt
	 *            the new updated at
	 */
	public void setUpdatedAt(Integer updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * @return the imageDefault
	 */
	public String getImageDefault() {
		return imageDefault;
	}

	/**
	 * @param imageDefault
	 *            the imageDefault to set
	 */
	public void setImageDefault(String imageDefault) {
		this.imageDefault = imageDefault;
	}

	/**
	 * @return the imageList
	 */
	public List<String> getImageList() {
		imageList = imageList == null ? new ArrayList<String>() : imageList;
		return imageList;
	}

	/**
	 * @param imageList
	 *            the imageList to set
	 */
	public void setImageList(List<String> imageList) {
		this.imageList = imageList;
	}

	/**
	 * Adds the image.
	 *
	 * @param image
	 *            the image
	 * @return the image
	 */
	public String addImage(String image) {
		getImageList().add(image);
		return image;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public ItemKey getItemKey() {
		return itemKey;
	}

	public void setItemKey(ItemKey itemKey) {
		this.itemKey = itemKey;
	}

	@Override
	public int hashCode() {
		return getItemKey().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return getItemKey().equals(obj);
	}

}