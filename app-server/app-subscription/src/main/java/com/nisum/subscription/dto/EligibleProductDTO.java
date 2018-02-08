package com.nisum.subscription.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The Class EligibleProductDTO.
 */
public class EligibleProductDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String productID;

	
	private String clientID;
	
	
	private String name; 
	
	
	private String description;
	
	
	private String delFrequency;
	
	
	private Double delCharge;
	
	
	private BigDecimal price;
	
	
	private String campaignID;
	
	
	public String getProductID() {
		return productID;
	}


	public void setProductID(String productID) {
		this.productID = productID;
	}


	public String getClientID() {
		return clientID;
	}


	public void setClientID(String clientID) {
		this.clientID = clientID;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getDelFrequency() {
		return delFrequency;
	}


	public void setDelFrequency(String delFrequency) {
		this.delFrequency = delFrequency;
	}


	public Double getDelCharge() {
		return delCharge;
	}


	public void setDelCharge(Double delCharge) {
		this.delCharge = delCharge;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public String getCampaignID() {
		return campaignID;
	}


	public void setCampaignID(String campaignID) {
		this.campaignID = campaignID;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	private String category;

}
