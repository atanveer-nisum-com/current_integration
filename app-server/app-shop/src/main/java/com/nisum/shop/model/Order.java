package com.nisum.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nisum.common.constant.OrderStatus;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;



// TODO: Auto-generated Javadoc
/**
 * The persistent class for the order database table.
 * 
 */
@Entity
@Table(name="orders")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
@SQLDelete(sql="Update Orders o SET o.order_status = 12 where o.id=?")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order extends BaseModel implements Serializable {
	
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

	/** The order status. */
	@Column(name="order_status", nullable=false)
	private Short orderStatus;

	/** The total item. */
	@Column(name="total_item", nullable=false)
	private Integer totalItem;

	/** The sub total price. */
	@Column(name="sub_total_price", nullable=false, precision=10)
	private BigDecimal subTotalPrice;

	/** The taxation. */
	@Column(name="taxation", nullable=false, precision=10)
	private BigDecimal taxation;
	
	/** The order total price. */
	@Column(name="order_total_price", nullable=false, precision=10)
	private BigDecimal orderTotalPrice;
	
	/** The updated at. */
	@Column(name="updated_at")
	private Long updatedAt;

	/** The address. */
	//bi-directional many-to-one association to Address
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="address_id", nullable=false)
	private Address address;

	/** The transaction. */
	//bi-directional many-to-one association to OrderTransaction
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="transaction_id", nullable=false)
	private OrderTransaction transaction;


	/** The order email links. */
	//bi-directional many-to-one association to OrderEmailLink
	@OneToMany(mappedBy="order")
	private Set<OrderEmailLink> orderEmailLinks;

	/** The order items. */
	//bi-directional many-to-one association to OrderItem
	@OneToMany(mappedBy="order")
	@Where(clause="is_deleted = 0")
	private Set<OrderItem> orderItems;
	
	/** The order status title. */
	@Transient
	private String orderStatusTitle;
	
	/** The user id. */
	@Column(name="user_id", nullable=false)
	private Long userId;

	/**
	 * Instantiates a new order.
	 */
	public Order() {
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
	 * Gets the order status.
	 *
	 * @return the order status
	 */
	public Short getOrderStatus() {
		return this.orderStatus;
	}

	/**
	 * Sets the order status.
	 *
	 * @param orderStatus the new order status
	 */
	public void setOrderStatus(Short orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 * Gets the total item.
	 *
	 * @return the total item
	 */
	public Integer getTotalItem() {
		return this.totalItem != null && this.totalItem < 0 ? 0 : this.totalItem;
	}

	/**
	 * Sets the total item.
	 *
	 * @param totalItem the new total item
	 */
	public void setTotalItem(Integer totalItem) {
		this.totalItem = totalItem;
	}

	/**
	 * Gets the sub total price.
	 *
	 * @return the sub total price
	 */
	public BigDecimal getSubTotalPrice() {
		return this.subTotalPrice;
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
	 * Gets the taxation.
	 *
	 * @return the taxation
	 */
	public BigDecimal getTaxation() {
		return taxation;
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
	 * Gets the order total price.
	 *
	 * @return the order total price
	 */
	public BigDecimal getOrderTotalPrice() {
		return orderTotalPrice;
	}

	/**
	 * Sets the order total price.
	 *
	 * @param orderTotalPrice the new order total price
	 */
	public void setOrderTotalPrice(BigDecimal orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
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
	 * Gets the address.
	 *
	 * @return the address
	 */
	public Address getAddress() {
		return this.address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * Gets the transaction.
	 *
	 * @return the transaction
	 */
	public OrderTransaction getTransaction() {
		return this.transaction;
	}

	/**
	 * Sets the transaction.
	 *
	 * @param transaction the new transaction
	 */
	public void setTransaction(OrderTransaction transaction) {
		this.transaction = transaction;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */


	/**
	 * Gets the order email links.
	 *
	 * @return the order email links
	 */
	public Set<OrderEmailLink> getOrderEmailLinks() {
		return this.orderEmailLinks;
	}

	/**
	 * Sets the order email links.
	 *
	 * @param orderEmailLinks the new order email links
	 */
	public void setOrderEmailLinks(Set<OrderEmailLink> orderEmailLinks) {
		this.orderEmailLinks = orderEmailLinks;
	}

	/**
	 * Adds the order email link.
	 *
	 * @param orderEmailLink the order email link
	 * @return the order email link
	 */
	public OrderEmailLink addOrderEmailLink(OrderEmailLink orderEmailLink) {
		getOrderEmailLinks().add(orderEmailLink);
		orderEmailLink.setOrder(this);

		return orderEmailLink;
	}

	/**
	 * Removes the order email link.
	 *
	 * @param orderEmailLink the order email link
	 * @return the order email link
	 */
	public OrderEmailLink removeOrderEmailLink(OrderEmailLink orderEmailLink) {
		getOrderEmailLinks().remove(orderEmailLink);
		orderEmailLink.setOrder(null);

		return orderEmailLink;
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

	/**
	 * Removes the order item.
	 *
	 * @param orderItem the order item
	 * @return the order item
	 */
	public OrderItem removeOrderItem(OrderItem orderItem) {
		getOrderItems().remove(orderItem);
		orderItem.setOrder(null);

		return orderItem;
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
		Order other = (Order) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
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
		if (orderEmailLinks == null) {
			if (other.orderEmailLinks != null)
				return false;
		} else if (!orderEmailLinks.equals(other.orderEmailLinks))
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

	/**
	 * Sets the order status title.
	 *
	 * @param orderStatusTitle the new order status title
	 */
	public void setOrderStatusTitle(String orderStatusTitle) {
		this.orderStatusTitle = orderStatusTitle;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	

}