package com.nisum.shop.model;

import com.nisum.common.util.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * The persistent class for the tax database table.
 *
 */
@Entity
@Table(name = "tax")
public class Tax extends BaseEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8749740661007490082L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long id;

	/** The state. */
	@Column(nullable = false, length = 50)
	private String state;

	/** The percentage. */
	@Column(nullable = false, precision = 10)
	private BigDecimal percentage;

	/** The abbrv. */
	@Column(nullable = false)
	private String abbrv;

	/**
	 * Instantiates a new tax.
	 */
	public Tax() {
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return this.id;
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
	 * Gets the percentage.
	 *
	 * @return the percentage
	 */
	public BigDecimal getPercentage() {
		return this.percentage;
	}

	/**
	 * Sets the percentage.
	 *
	 * @param percentage the new percentage
	 */
	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Gets the abbrv.
	 *
	 * @return the abbrv
	 */
	public String getAbbrv() {
		return abbrv;
	}

	/**
	 * Sets the abbrv.
	 *
	 * @param abbrv the new abbrv
	 */
	public void setAbbrv(String abbrv) {
		this.abbrv = abbrv;
	}

	/* (non-Javadoc)
	 * @see com.nisum.model.BaseModel#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((abbrv == null) ? 0 : abbrv.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see com.nisum.model.BaseModel#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tax other = (Tax) obj;
		if (abbrv == null) {
			if (other.abbrv != null)
				return false;
		} else if (!abbrv.equals(other.abbrv))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



}