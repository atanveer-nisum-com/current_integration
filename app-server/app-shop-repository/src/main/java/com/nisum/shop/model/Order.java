package com.nisum.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nisum.common.constant.OrderStatus;
import com.nisum.common.util.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * The persistent class for the order database table.
 *
 */
@Entity
@Table(name="orders")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
@SQLDelete(sql="Update Orders o SET o.order_status = 12 where o.id=?")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order extends BaseEntity implements Serializable {

	/** The Constant serialVersionUID. */
private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Long id;

	/** The address. */
	//bi-directional many-to-one association to Address
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="address_id", nullable=false)
	@Column(name="address_id", nullable=false)
	private Long addressId;

	@Column(name="created_at", nullable=false)
	private Long createdAt;

	@Column(name="order_status", nullable=false)
	private Short orderStatus;

	@Column(name="order_total_price", nullable=false, precision=10)
	private BigDecimal orderTotalPrice;

	@Column(name="sub_total_price", nullable=false, precision=10)
	private BigDecimal subTotalPrice;

	@Column(name="taxation", nullable=false, precision=10)
	private BigDecimal taxation;

	@Column(name="total_item", nullable=false)
	private Long totalItem;

	@Column(name="updated_at")
	private Long updatedAt;

	@Column(name="user_id")
	private Long userId;
	
	/** The order status title. */
	@Transient
	private String orderStatusTitle;

	//bi-directional many-to-one association to OrderItem
	@OneToMany(mappedBy="order")
	@Where(clause="is_deleted = 0")
	private Set<OrderItem> orderItems;

	//bi-directional many-to-one association to Transaction
	@ManyToOne(fetch=FetchType.LAZY)
	private Transaction transaction;

	public Order() {
	}

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getAddressId() {
		return addressId;
	}


	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}


	public Long getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}


	public Short getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(Short orderStatus) {
		this.orderStatus = orderStatus;
	}


	public BigDecimal getOrderTotalPrice() {
		return orderTotalPrice;
	}


	public void setOrderTotalPrice(BigDecimal orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}


	public BigDecimal getSubTotalPrice() {
		return subTotalPrice;
	}


	public void setSubTotalPrice(BigDecimal subTotalPrice) {
		this.subTotalPrice = subTotalPrice;
	}


	public BigDecimal getTaxation() {
		return taxation;
	}


	public void setTaxation(BigDecimal taxation) {
		this.taxation = taxation;
	}


	public Long getTotalItem() {
		return this.totalItem != null && this.totalItem < 0 ? 0 : this.totalItem;
	}


	public void setTotalItem(Long totalItem) {
		this.totalItem = totalItem;
	}


	public Long getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	/**
	 * Gets the order status title.
	 *
	 * @return the order status title
	 */
	public String getOrderStatusTitle() {
		switch(this.orderStatus){
			case 0:
				orderStatusTitle = OrderStatus.AWAITING_PAYMENT.getTitle();
				break;
			case 1:
				orderStatusTitle = OrderStatus.AWAITING_FULFILLMENT.getTitle();
				break;
			case 2:
				orderStatusTitle = OrderStatus.AWAITING_SHIPMENT.getTitle();
				break;
			case 3:
				orderStatusTitle = OrderStatus.AWAITING_PICKUP.getTitle();
				break;
			case 4:
				orderStatusTitle = OrderStatus.PARTIALLY_SHIPPED.getTitle();
				break;
			case 5:
				orderStatusTitle = OrderStatus.COMPLETED.getTitle();
				break;
			case 6:
				orderStatusTitle = OrderStatus.SHIPPED.getTitle();
				break;
			case 7:
				orderStatusTitle = OrderStatus.CANCELLED.getTitle();
				break;
			case 8:
				orderStatusTitle = OrderStatus.DECLINED.getTitle();
				break;
			case 9:
				orderStatusTitle = OrderStatus.REFUNDED.getTitle();
				break;
			case 10:
				orderStatusTitle = OrderStatus.DISPUTED.getTitle();
				break;
			case 11:
				orderStatusTitle = OrderStatus.VARIFICATION_REQUIRED.getTitle();
				break;
			case 12:
				orderStatusTitle = OrderStatus.MERGE.getTitle();
				break;
		}

		return orderStatusTitle;
	}



	public void setOrderStatusTitle(String orderStatusTitle) {
		this.orderStatusTitle = orderStatusTitle;
	}

	/**
	 * Gets the order items.
	 *
	 * @return the order items
	 */
	public Set<OrderItem> getOrderItems() {
		return this.orderItems;
	}

	/**
	 * Sets the order items.
	 *
	 * @param orderItems the new order items
	 */
	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	/**
	 * Adds the order item.
	 *
	 * @param orderItem the order item
	 * @return the order item
	 */
	public OrderItem addOrderItem(OrderItem orderItem) {
		if (getOrderItems() == null) {
			Set<OrderItem> orderItemSet = new HashSet<OrderItem>();
			orderItemSet.add(orderItem);
			setOrderItems(orderItemSet);
		}else {
			getOrderItems().add(orderItem);
		}
		orderItem.setOrder(this);

		return orderItem;
	}

	public OrderItem removeOrderItem(OrderItem orderItem) {
		getOrderItems().remove(orderItem);
		orderItem.setOrder(null);

		return orderItem;
	}

	public Transaction getTransaction() {
		return this.transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
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
		Order other = (Order) obj;
		if (addressId == null) {
			if (other.addressId != null)
				return false;
		} else if (!addressId.equals(other.addressId))
			return false;
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
		if (orderItems == null) {
			if (other.orderItems != null)
				return false;
		} else if (!orderItems.equals(other.orderItems))
			return false;
		if (orderStatus == null) {
			if (other.orderStatus != null)
				return false;
		} else if (!orderStatus.equals(other.orderStatus))
			return false;
		if (totalItem == null) {
			if (other.totalItem != null)
				return false;
		} else if (!totalItem.equals(other.totalItem))
			return false;
		if (subTotalPrice == null) {
			if (other.subTotalPrice != null)
				return false;
		} else if (!subTotalPrice.equals(other.subTotalPrice))
			return false;
		if (taxation == null) {
			if (other.taxation != null)
				return false;
		} else if (!taxation.equals(other.taxation))
			return false;
		if (orderTotalPrice == null) {
			if (other.orderTotalPrice != null)
				return false;
		} else if (!orderTotalPrice.equals(other.orderTotalPrice))
			return false;
		if (transaction == null) {
			if (other.transaction != null)
				return false;
		} else if (!transaction.equals(other.transaction))
			return false;
		if (updatedAt == null) {
			if (other.updatedAt != null)
				return false;
		} else if (!updatedAt.equals(other.updatedAt))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
}