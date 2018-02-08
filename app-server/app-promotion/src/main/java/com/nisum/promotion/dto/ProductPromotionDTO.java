package com.nisum.promotion.dto;


/**
 * @author Kahmed
 *
 */

public class ProductPromotionDTO implements ProductPromotion {

	private String productId;
	private String defaultDiscount;
	private String active;
	private String productDiscount;
	
	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * @return the active
	 */
	public String getActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(String active) {
		this.active = active;
	}
	public String getDefaultDiscount() {
		return defaultDiscount;
	}
	public void setDefaultDiscount(String defaultDiscount) {
		this.defaultDiscount = defaultDiscount;
	}
	public String getProductDiscount() {
		return productDiscount;
	}
	public void setProductDiscount(String productDiscount) {
		this.productDiscount = productDiscount;
	}
	
	
}
