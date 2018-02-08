/**
 * 
 */
package com.nisum.promotion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;


/**
 * @author Nisum Pakistan
 * The persistent class for the promotion_type database table.
 *
 */
@Entity
@Table(name="promotion_type")
@NamedQuery(name="PromotionType.findAll", query="SELECT p FROM PromotionType p")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PromotionType implements Serializable {
	
	/** */
	private static final long serialVersionUID = 1L;
	/** */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;
	
	/** */
	@Column(name="promotion_desc")
	private String promotionDesc;

	/** */
	@Column(name="promotion_name")
	private String promotionName;

	/** */
	@OneToOne(mappedBy="promotionType",fetch=FetchType.LAZY)
	private PromotionDiscount promotionDiscount;

	/**
	 * 
	 */
	public PromotionType() {
	}

	/**
	 * 
	 * @return
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getPromotionDesc() {
		return this.promotionDesc;
	}
	
	/**
	 * 
	 * @param promotionDesc
	 */
	public void setPromotionDesc(String promotionDesc) {
		this.promotionDesc = promotionDesc;
	}

	/**
	 * 
	 * @return
	 */
	public String getPromotionName() {
		return this.promotionName;
	}
	
	/**
	 * 
	 * @param promotionName
	 */
	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}

	

	/**
	 * @return the promotionDiscount
	 */
	public PromotionDiscount getPromotionDiscount() {
		return promotionDiscount;
	}

	/**
	 * @param promotionDiscount the promotionDiscount to set
	 */
	public void setPromotionDiscount(PromotionDiscount promotionDiscount) {
		this.promotionDiscount = promotionDiscount;
	}

}
