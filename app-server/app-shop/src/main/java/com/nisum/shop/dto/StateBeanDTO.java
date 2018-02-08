
package com.nisum.shop.dto;

import java.io.Serializable;


/**
 * The Class StateBeanDTO.
 */
public class StateBeanDTO implements Serializable
{

    /** The id. */
    private Integer id;
    
    /** The name. */
    private String name;
    
    /** The Constant serialVersionUID. */
    private final static long serialVersionUID = -1184261766030247804L;

    public StateBeanDTO() {
	}
    
    
    
    public StateBeanDTO(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}



	/**
     * Gets the id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(Integer id) {
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
	 * Sets the name.
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
    
    

}
