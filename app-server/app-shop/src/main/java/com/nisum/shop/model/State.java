package com.nisum.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


/**
 * The persistent class for the country database table.
 * 
 */
@Entity
@Table(name = "state")
@JsonIgnoreProperties({ "addresses", "country", "hibernateLazyInitializer", "handler" })
public class State extends BaseModel implements Serializable {
	

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6607401742280314154L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long id;

	/** The name. */
	@Column( length = 100)
	private String name;

	/** The abbreviation. */
	@Column(length = 10)
	private String abbreviation;
	
	/** The country. */
	//bi-directional many-to-one association to Country
	@ManyToOne()
	@JoinColumn(name="country_id")
	private Country country;

	/** The addresses. */
	// bi-directional many-to-one association to Address
	@OneToMany(mappedBy = "stateBean", fetch = FetchType.LAZY)
	private Set<Address> addresses;

	/**
	 * Instantiates a new state.
	 */
	public State() {
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
		address.setStateBean(this);

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
		address.setStateBean(null);

		return address;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(Country country) {
		this.country = country;
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
		State other = (State) obj;
		if (id != other.id)
			return false;
		return true;
	}

}