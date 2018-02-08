package com.nisum.common.shop.dto;

import java.math.BigDecimal;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO extends ShopBaseDTO{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long orderId;

	/** The created at. */
	private Long createdAt;

	/** The order status. */
	private Short orderStatus;

	/** The total item. */
	private Integer totalItem;

	/** The order total price. */
	private BigDecimal orderTotalPrice;
	
	/** The order items. */
	private Set<OrderItemDTO> orderItems;
	
	/** The Payment Type. */
	private String paymentType;
	
	public OrderDTO() {
		
	}
	
	public OrderDTO (Long orderId, Long createdAt, Short orderStatus, Integer totalItem, BigDecimal orderTotalPrice) {
		this.orderId = orderId;
		this.createdAt = createdAt;
		this.orderStatus = orderStatus;
		this.totalItem = totalItem;
		this.orderTotalPrice = orderTotalPrice;
		this.paymentType = getPaymentType();
	}
	
	
	public String getPaymentType() {
		return (paymentType != null) ? paymentType : "USD";
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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

	public Integer getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(Integer totalItem) {
		this.totalItem = totalItem;
	}


	public BigDecimal getOrderTotalPrice() {
		return orderTotalPrice;
	}

	public void setOrderTotalPrice(BigDecimal orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}

	public Set<OrderItemDTO> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItemDTO> orderItems) {
		this.orderItems = orderItems;
	}
}
