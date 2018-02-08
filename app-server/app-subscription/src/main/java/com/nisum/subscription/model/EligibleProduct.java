package com.nisum.subscription.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

import com.datastax.driver.mapping.annotations.Table;

@Table(name="eligibleproducts")
public class EligibleProduct implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4445708831434851320L;
	@PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private String productID;

	@PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private String clientID;
	
	@Column
	private String name; 
	
	@Column
	private String description;
	
	@Column
	private String delFrequency;
	
	@Column
	private Double delCharge;
	
	@Column
	private BigDecimal price;
	
	@Column
	private String campaignID;
	
	@Column
	private String category;

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
	
	
}
