package com.nisum.fcc.cassandra.model;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Table(name="productbycategory")
public class ProductByCategory extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4445708831464851320L;

	@PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private String category;  

	@PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.CLUSTERED)
	private String name;
	
	@Column
	private String code;
	
	@Column
	private Byte isDeleted;

	@Column
	private String id;

	@Column
	private String sku;

	
	/** The description. */
	@Column(name = "description")
	private String description;

	/** The quantity. */
	@Column(name = "quantity")
	private Integer quantity;

	/** The is buyable. */
	@Column(name = "buyable")
	private Byte isBuyable;

	/** The is displayable. */
	@Column(name = "displayable")
	private Byte isDisplayable;

	/** The created at. */
	@Column(name = "created_at")
	private Integer createdAt;

	/** The updated at. */
	@Column(name = "updated_at")
	private Integer updatedAt;

	@Column(name = "image_default")
	private String imageDefault;

	/** The image List. */
	@Column(name = "images_list")
	private List<String> imageList;

	/** The price. */
	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "is_recommended")
	private Byte isRecommended;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Byte isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Byte getIsRecommended() {
		return isRecommended;
	}

	public void setIsRecommended(Byte isRecommended) {
		this.isRecommended = isRecommended;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Byte getIsBuyable() {
		return isBuyable;
	}

	public void setIsBuyable(Byte isBuyable) {
		this.isBuyable = isBuyable;
	}

	public Byte getIsDisplayable() {
		return isDisplayable;
	}

	public void setIsDisplayable(Byte isDisplayable) {
		this.isDisplayable = isDisplayable;
	}

	public Integer getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Integer createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Integer updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getImageDefault() {
		return imageDefault;
	}

	public void setImageDefault(String imageDefault) {
		this.imageDefault = imageDefault;
	}

	public List<String> getImageList() {
		imageList = imageList == null ? new ArrayList<>() : imageList;
		return imageList;
	}

	public void setImageList(List<String> imageList) {
		this.imageList = imageList;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.code).append(this.name).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ProductByCategory == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		final ProductByCategory otherObject = (ProductByCategory) obj;

		return new EqualsBuilder().append(this.code, otherObject.code).append(this.name, otherObject.name).isEquals();
	}


}
