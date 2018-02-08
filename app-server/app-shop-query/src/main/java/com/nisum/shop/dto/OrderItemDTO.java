package com.nisum.shop.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nisum.shop.api.dto.ProductDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class OrderItemDTO.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItemDTO  {

	/** The id. */
	private Long id;
	
	/** The inventory status. */
	private Short inventoryStatus;
	
	/** The price. */
	private BigDecimal price;
	
	/** The sub total price. */
	private BigDecimal subTotalPrice;
	
	/** The quantity. */
	private Integer quantity;
	
	/** The taxation. */
	private BigDecimal taxation;

	/** The total price. */
	private BigDecimal totalPrice;
	
	/** The product. */
	private ProductDTO product;

	private String name;
	
	private String code;
	
	private String imagePath;
	

	public OrderItemDTO(Long id, BigDecimal price, Integer quantity, String code, String name, String imagePath) {
		super();
		this.id = id;
		this.price = price;
		this.quantity = quantity;
		this.code = code;
		this.imagePath = imagePath;
		this.name = name;
	}
	
	
	
	/**
	 * Instantiates a new order item DTO.
	 *
	 * @param id the id
	 * @param inventoryStatus the inventory status
	 * @param price the price
	 * @param subTotalPrice the sub total price
	 * @param quantity the quantity
	 * @param taxation the taxation
	 * @param totalPrice the total price
	 * @param productDTO the product DTO
	 */
	public OrderItemDTO(Long id, Short inventoryStatus, BigDecimal price, BigDecimal subTotalPrice, Integer quantity,
			BigDecimal taxation, BigDecimal totalPrice, ProductDTO productDTO) {
		super();
		this.id = id;
		this.inventoryStatus = inventoryStatus;
		this.price = price;
		this.subTotalPrice = subTotalPrice;
		this.quantity = quantity;
		this.taxation = taxation;
		this.totalPrice = totalPrice;
		this.product = productDTO;
	}
	
	/**
	 * Instantiates a new order item DTO.
	 *
	 * @param id the id
	 * @param inventoryStatus the inventory status
	 * @param price the price
	 * @param subTotalPrice the sub total price
	 * @param quantity the quantity
	 * @param taxation the taxation
	 * @param totalPrice the total price
	 */
	public OrderItemDTO(Long id, Short inventoryStatus, BigDecimal price, BigDecimal subTotalPrice, Integer quantity,
			BigDecimal taxation, BigDecimal totalPrice) {
		super();
		this.id = id;
		this.inventoryStatus = inventoryStatus;
		this.price = price;
		this.subTotalPrice = subTotalPrice;
		this.quantity = quantity;
		this.taxation = taxation;
		this.totalPrice = totalPrice;
		
	}

	/**
	 * Instantiates a new order item DTO.
	 */
	public OrderItemDTO() {
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the inventory status.
	 *
	 * @return the inventory status
	 */
	public Short getInventoryStatus() {
		return inventoryStatus;
	}

	/**
	 * Sets the inventory status.
	 *
	 * @param inventoryStatus the new inventory status
	 */
	public void setInventoryStatus(Short inventoryStatus) {
		this.inventoryStatus = inventoryStatus;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * Gets the sub total price.
	 *
	 * @return the sub total price
	 */
	public BigDecimal getSubTotalPrice() {
		return subTotalPrice;
	}

	/**
	 * Sets the sub total price.
	 *
	 * @param subTotalPrice the new sub total price
	 */
	public void setSubTotalPrice(BigDecimal subTotalPrice) {
		this.subTotalPrice = subTotalPrice;
	}

	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * Gets the taxation.
	 *
	 * @return the taxation
	 */
	public BigDecimal getTaxation() {
		return taxation;
	}

	/**
	 * Sets the taxation.
	 *
	 * @param taxation the new taxation
	 */
	public void setTaxation(BigDecimal taxation) {
		this.taxation = taxation;
	}

	/**
	 * Gets the total price.
	 *
	 * @return the total price
	 */
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	/**
	 * Sets the total price.
	 *
	 * @param totalPrice the new total price
	 */
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * Gets the product.
	 *
	 * @return the product
	 */
	public ProductDTO getProduct() {
		return product;
	}

	/**
	 * Sets the product.
	 *
	 * @param product the new product
	 */
	public void setProduct(ProductDTO product) {
		this.product = product;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getImagePath() {
		return imagePath;
	}



	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
	
}
