package com.nisum.shop.dto;

import java.io.Serializable;


/**
 * The Class SearchTerm.
 */
public class SearchTerm implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1003785786561502247L;

	/** The name. */
	private String name;
	
	/** The id. */
	private Long id;
	
	/**
	 * Instantiates a new search term.
	 *
	 * @param id the id
	 * @param name the name
	 */
	public SearchTerm(Long id,String name) {
		super();
		this.name = name;
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	
}
