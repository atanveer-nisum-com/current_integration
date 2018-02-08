package com.nisum.promotion.dto;



public class ProductCategoryPromotionDTO implements ProductCategoryPromotion {

	private String categoryName;
	private String active;
	private String defaultDiscount;
	private String productDiscount;
	private String productId;
	private String promotionType;
	
	@Override
	public String getCategoryName() {
		// TODO Auto-generated method stub
		return categoryName;
	}

	@Override
	public String getActive() {
		// TODO Auto-generated method stub
		return active;
	}

	@Override
	public String getProductId() {
		// TODO Auto-generated method stub
		return productId;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public void setProductId(String productId) {
		this.productId = productId;
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

	public String getPromotionType() {
		return promotionType;
	}

	public void setPromotionType(String promotionType) {
		this.promotionType = promotionType;
	}


}
