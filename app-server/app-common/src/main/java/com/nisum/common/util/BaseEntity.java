package com.nisum.common.util;

public abstract class BaseEntity {
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	abstract public int hashCode();
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	abstract public boolean equals(Object obj);
}
