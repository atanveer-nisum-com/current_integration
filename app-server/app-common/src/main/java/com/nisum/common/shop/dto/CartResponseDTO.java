package com.nisum.common.shop.dto;

import java.math.BigDecimal;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartResponseDTO extends ShopBaseDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private TokenDTO userToken;
	
	private Integer totalItem;
	
	private BigDecimal subTotalPrice;
	
	private BigDecimal taxation;
	
	private BigDecimal orderTotalPrice;
	
	private Set<OrderItemDTO> orderItems;
	
	

	public CartResponseDTO(Long id, Integer totalItem, BigDecimal subTotalPrice, BigDecimal taxation,
			BigDecimal orderTotalPrice, Set<OrderItemDTO> orderItems) {
		super();
		this.id = id;
		this.totalItem = totalItem;
		this.subTotalPrice = subTotalPrice;
		this.taxation = taxation;
		this.orderTotalPrice = orderTotalPrice;
		this.orderItems = orderItems;
	}

	public CartResponseDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(Integer totalItem) {
		this.totalItem = totalItem;
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

	public TokenDTO getUserToken() {
		return userToken;
	}

	public void setUserToken(TokenDTO userToken) {
		this.userToken = userToken;
	}

}
