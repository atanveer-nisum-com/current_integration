/**
 * 
 */
package com.nisum.promotion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Nisum Pakistan
 *
 */
/**
 * The persistent class for the promotion_discount database table.
 * 
 */
@Entity
@Table(name="promotion_discount")
@NamedQuery(name="PromotionDiscount.findAll", query="SELECT p FROM PromotionDiscount p")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","promotionType"})
public class PromotionDiscount implements Serializable {
	
	/**Default constructor  */
	public PromotionDiscount() {
	}
	
	/** SerialVersion Id   */
	private static final long serialVersionUID = 1L;

	/**The Id field which is unique */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;
	
	/**The active field used for promotion activation */
	@Column(name = "active")
	private byte active;

	/**The createdAt field */
	@Column(name="created_at")
	private int createdAt;

	/**The discount value field which stores the discount value N* number of products */
	@Column(name="discount_value")
	private String discountValue;

	/**The updatedAt field */
	@Column(name="updated_at",nullable=true)
	private Long updatedAt;
	
	/**The promotionType field.Hold the reference of PromotionType Object */
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="promotion_type_id",nullable=false)
	private PromotionType promotionType;
	
	
	
	/** Get the Id
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/** Set the Id
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/** Get the active flag
	 * @return the active
	 */
	public byte getActive() {
		return active;
	}

	/** set the active flag
	 * @param active the active to set
	 */
	public void setActive(byte active) {
		this.active = active;
	}

	/** Get the createdAt 
	 * @return the createdAt
	 */
	public int getCreatedAt() {
		return createdAt;
	}

	/** Set the createdAt
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(int createdAt) {
		this.createdAt = createdAt;
	}

	/** Get the discountValue
	 * @return the discountValue
	 */
	public String getDiscountValue() {
		return discountValue;
	}

	/** Set the discountValue
	 * @param discountValue the discountValue to set
	 */
	public void setDiscountValue(String discountValue) {
		this.discountValue = discountValue;
	}

	/** Get the updatedAt
	 * @return the updatedAt
	 */
	public Long getUpdatedAt() {
		return updatedAt;
	}

	/**Set the updatedAt
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**Get the promotionType
	 * @return the promotionType
	 */
	public PromotionType getPromotionType() {
		return promotionType;
	}

	/**Set the promotionType
	 * @param promotionType the promotionType to set
	 */
	public void setPromotionType(PromotionType promotionType) {
		this.promotionType = promotionType;
	}


}
