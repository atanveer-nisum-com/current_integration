package com.nisum.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.nisum.common.constant.OrderStatus;
import com.nisum.common.event.message.dto.EtaEvent;

public class Order extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1690628519494843251L;

	private String id;

	private Long createdAt;

	private Short orderStatus;

	private Integer totalItem;

	private BigDecimal subTotalPrice;

	private BigDecimal taxation;
	
	private BigDecimal orderTotalPrice;
	
	private Long updatedAt;
	
	private String orderStatusTitle;
	
	private Long userId;
	
	private List<String> items;

	public List<String> getItems() {
		return items;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}

	public Order() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
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

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public Short getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(Short orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getTotalItem() {
		return this.totalItem != null && this.totalItem < 0 ? 0 : this.totalItem;
	}

	public void setTotalItem(Integer totalItem) {
		this.totalItem = totalItem;
	}

	public BigDecimal getSubTotalPrice() {
		return this.subTotalPrice;
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

	public BigDecimal getOrderTotalPrice() {
		return orderTotalPrice;
	}

	public void setOrderTotalPrice(BigDecimal orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}

	public Long getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}


	public Order addOrderItem(Order order, String itemId) {
		List<String> oItems = new ArrayList<String>();
		if (order.getItems() != null) {
			oItems = order.getItems();
		}
		oItems.add(itemId);
		order.setItems(oItems);
		EtaEvent eventDto = new EtaEvent();
		eventDto.setObjectType("Whatever");
		
		
		return order;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}


}