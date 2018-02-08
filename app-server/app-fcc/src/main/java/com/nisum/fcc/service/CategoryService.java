package com.nisum.fcc.service;

import java.util.List;

import com.nisum.fcc.dto.CategoryDTO;


/**
 * The Interface CategoryService.
 */
public interface CategoryService {

	/**
	 * Gets the categories.
	 *
	 * @return the categories
	 */
	public List<CategoryDTO> getCategories();

	

}
