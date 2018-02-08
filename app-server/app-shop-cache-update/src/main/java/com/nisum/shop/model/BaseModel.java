package com.nisum.shop.model;



/**
 * Base class of all persistence classes.
 * 
 */

public abstract class BaseModel {

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	abstract public int hashCode();
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	abstract public boolean equals(Object obj);
	
}
