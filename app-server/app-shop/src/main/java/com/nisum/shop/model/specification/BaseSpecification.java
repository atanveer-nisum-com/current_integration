package com.nisum.shop.model.specification;


/**
 * The Class BaseSpecification.
 */
public class BaseSpecification {
	
	/**
	 * Gets the contains like pattern.
	 *
	 * @param searchTerm the search term
	 * @return the contains like pattern
	 */
	protected static String getContainsLikePattern(String searchTerm) {
		if (searchTerm == null || searchTerm.isEmpty()) {
			return "%";
		} else {
			return "%" + searchTerm.toLowerCase() + "%";
		}
	}

}
