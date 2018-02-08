package com.nisum.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the country database table.
 * 
 */
@Entity
@Table(name = "country")
@JsonIgnoreProperties({ "addresses","states","hibernateLazyInitializer", "handler" })
public class Country extends BaseModel implements Serializable {
	

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6606401742280304154L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long id;

	/** The name. */
	@Column(length = 100)
	private String name;

	/** The abbreviation. */
	@Column(length = 2)
	private String abbreviation;

	/** The addresses. */
	// bi-directional many-to-one association to Address
	@OneToMany(mappedBy = "countryBean", fetch = FetchType.LAZY)
	private Set<Address> addresses;
	
	/** The states. */
	//bi-directional many-to-one association to Country
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="country")
	private List<State> states;


	/**
	 * Instantiates a new country.
	 */
	public Country() {
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the abbreviation.
	 *
	 * @return the abbreviation
	 */
	public String getAbbreviation() {
		return abbreviation;
	}

	/**
	 * Sets the abbreviation.
	 *
	 * @param abbreviation the new abbreviation
	 */
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	/**
	 * Gets the addresses.
	 *
	 * @return the addresses
	 */
	public Set<Address> getAddresses() {
		return this.addresses;
	}

	/**
	 * Sets the addresses.
	 *
	 * @param addresses the new addresses
	 */
	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	/**
	 * Adds the address.
	 *
	 * @param address the address
	 * @return the address
	 */
	public Address addAddress(Address address) {
		getAddresses().add(address);
		address.setCountryBean(this);

		return address;
	}

	/**
	 * Removes the address.
	 *
	 * @param address the address
	 * @return the address
	 */
	public Address removeAddress(Address address) {
		getAddresses().remove(address);
		address.setCountryBean(null);

		return address;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (id == null ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (id != other.id)
			return false;
		return true;
	}

}