package com.nisum.promotion.dto;


public class CategoryPromotionDTO implements CategoryPromotion{

	private String categoryName;
	private String defaultDiscount;
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getDefaultDiscount() {
		return defaultDiscount;
	}
	public void setDefaultDiscount(String defaultDiscount) {
		this.defaultDiscount = defaultDiscount;
	}
	
	
	


}
