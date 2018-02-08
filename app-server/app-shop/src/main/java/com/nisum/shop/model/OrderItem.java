package com.nisum.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nisum.common.util.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;



// TODO: Auto-generated Javadoc
/**
 * The persistent class for the order_item database table.
 * 
 */
@Entity
@Table(name="order_item")
@SQLDelete(sql="Update order_item o SET o.is_deleted = 1 where o.id=?")
@Where(clause="is_deleted = 0" )
@JsonIgnoreProperties({"order"})
public class OrderItem extends BaseEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Long id;

	/** The created at. */
	@Column(name="created_at", nullable=false)
	private Long createdAt;

	/** The inventory status. */
	@Column(name="inventory_status", nullable=false)
	private Short inventoryStatus;

	/** The price. */
	@Column(nullable=false, precision=10)
	private BigDecimal price;
	
	/** The sub total price. */
	@Column(name="sub_total_price", nullable=false, precision=10)
	private BigDecimal subTotalPrice;

	/** The quantity. */
	@Column(nullable=false)
	private Integer quantity;

	/** The taxation. */
	@Column(nullable=false, precision=10)
	private BigDecimal taxation;

	/** The total price. */
	@Column(name="total_price", nullable=false, precision=10)
	private BigDecimal totalPrice;

	/** The updated at. */
	@Column(name="updated_at")
	private Long updatedAt;
	
	/** The is deleted. */
	@Column(name="is_deleted", nullable=false)
	private Byte isDeleted;


	/** The order. */
	//bi-directional many-to-one association to Order
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="order_id", nullable=false)
	private Order order;
	
	/**  The Item UUID. */
	@Column(name="item_uuid")
	private String itemUUID;

	/**
	 * Instantiates a new order item.
	 */
	public OrderItem() {
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
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
	 * Gets the inventory status.
	 *
	 * @return the inventory status
	 */
	public Short getInventoryStatus() {
		return this.inventoryStatus;
	}

	/**
	 * Sets the inventory status.
	 *
	 * @param inventoryStatus the new inventory status
	 */
	public void setInventoryStatus(Short inventoryStatus) {
		this.inventoryStatus = inventoryStatus;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return this.price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * Gets the sub total price.
	 *
	 * @return the sub total price
	 */
	public BigDecimal getSubTotalPrice() {
		return subTotalPrice;
	}

	/**
	 * Sets the sub total price.
	 *
	 * @param subTotalPrice the new sub total price
	 */
	public void setSubTotalPrice(BigDecimal subTotalPrice) {
		this.subTotalPrice = subTotalPrice;
	}

	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return this.quantity;
	}

	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * Gets the taxation.
	 *
	 * @return the taxation
	 */
	public BigDecimal getTaxation() {
		return this.taxation;
	}

	/**
	 * Sets the taxation.
	 *
	 * @param taxation the new taxation
	 */
	public void setTaxation(BigDecimal taxation) {
		this.taxation = taxation;
	}

	/**
	 * Gets the total price.
	 *
	 * @return the total price
	 */
	public BigDecimal getTotalPrice() {
		return this.totalPrice;
	}

	/**
	 * Sets the total price.
	 *
	 * @param totalPrice the new total price
	 */
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * Gets the updated at.
	 *
	 * @return the updated at
	 */
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
	 * Gets the checks if is deleted.
	 *
	 * @return the checks if is deleted
	 */
	public Byte getIsDeleted() {
		return this.isDeleted;
	}

	/**
	 * Sets the checks if is deleted.
	 *
	 * @param isDeleted the new checks if is deleted
	 */
	public void setIsDeleted(Byte isDeleted) {
		this.isDeleted = isDeleted;
	}
	

	/**
	 * Gets the order.
	 *
	 * @return the order
	 */
	public Order getOrder() {
		return this.order;
	}

	/**
	 * Sets the order.
	 *
	 * @param order the new order
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

	/**
	 * Gets the item UUID.
	 *
	 * @return the item UUID
	 */
	public String getItemUUID() {
		return itemUUID;
	}

	/**
	 * Sets the item UUID.
	 *
	 * @param itemUUID the new item UUID
	 */
	public void setItemUUID(String itemUUID) {
		this.itemUUID = itemUUID;
	}

	
	/* (non-Javadoc)
	 * @see com.nisum.model.BaseModel#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see com.nisum.model.BaseModel#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inventoryStatus == null) {
			if (other.inventoryStatus != null)
				return false;
		} else if (!inventoryStatus.equals(other.inventoryStatus))
			return false;
		if (itemUUID == null) {
			if (other.itemUUID != null)
				return false;
		} else if (!itemUUID.equals(other.itemUUID))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (subTotalPrice == null) {
			if (other.subTotalPrice != null)
				return false;
		} else if (!subTotalPrice.equals(other.subTotalPrice))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (taxation == null) {
			if (other.taxation != null)
				return false;
		} else if (!taxation.equals(other.taxation))
			return false;
		if (totalPrice == null) {
			if (other.totalPrice != null)
				return false;
		} else if (!totalPrice.equals(other.totalPrice))
			return false;
		if (updatedAt == null) {
			if (other.updatedAt != null)
				return false;
		} else if (!updatedAt.equals(other.updatedAt))
			return false;
		if (isDeleted == null) {
			if (other.isDeleted != null)
				return false;
		} else if (!isDeleted.equals(other.isDeleted))
			return false;
		return true;
	}

}