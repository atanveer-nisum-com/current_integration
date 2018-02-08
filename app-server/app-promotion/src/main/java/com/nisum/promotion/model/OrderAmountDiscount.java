/**
 * 
 */
package com.nisum.promotion.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;



/**
 * @author Nisum Paksitan
 * The persistent class for the order_amount_discount database table.
 *
 */
@Entity
@Table(name="order_amount_discount")
public class OrderAmountDiscount implements Serializable {

	/** */
	private static final long serialVersionUID = 5138658127575251360L;
	
	/** */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Long id;
	
	/** */
	@Column(name = "from_amount")
	private BigDecimal fromAmount;
	
	
	/** */
	@Column(name = "to_amount")
	private BigDecimal toAmount;
	
	/** */
	@Column(name = "discount_value")
	private BigDecimal discountValue;
	
	/** */
	@Column (name = "active")
	private byte active;
	
	/** */
	@Column(name = "created_at")
	private Long createdAt;
	
	/** */
	@Column (name = "updated_at")
	private Long updatedAt;
	
	/** */
	@Column (name = "start_date")
	private Long startDate;
	
	/** */
	@Column (name = "end_date")
	private Long endDate;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the fromAmount
	 */
	public BigDecimal getFromAmount() {
		return fromAmount;
	}

	/**
	 * @param fromAmount the fromAmount to set
	 */
	public void setFromAmount(BigDecimal fromAmount) {
		this.fromAmount = fromAmount;
	}

	/**
	 * @return the toAmount
	 */
	public BigDecimal getToAmount() {
		return toAmount;
	}

	/**
	 * @param toAmount the toAmount to set
	 */
	public void setToAmount(BigDecimal toAmount) {
		this.toAmount = toAmount;
	}

	/**
	 * @return the discountValue
	 */
	public BigDecimal getDiscountValue() {
		return discountValue;
	}

	/**
	 * @param discountValue the discountValue to set
	 */
	public void setDiscountValue(BigDecimal discountValue) {
		this.discountValue = discountValue;
	}

	/**
	 * @return the active
	 */
	public byte getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(byte active) {
		this.active = active;
	}

	/**
	 * @return the createdAt
	 */
	public Long getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the updatedAt
	 */
	public Long getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * @return the startDate
	 */
	public Long getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Long getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}
	
	
	
}
