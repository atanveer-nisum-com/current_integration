package com.nisum.shop.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * CategoryJSON is a DTO for managing category and sub-categories hierarchy.
 * 
 */

public class CategoryDTO extends BaseDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;

	private List<SubCategoryDTO> subCategories = new ArrayList<>();

	public CategoryDTO() {
	}

	public CategoryDTO(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public CategoryDTO(String name, List<String> subCategories) {
		this.name = name;
		this.id= name;
		if (subCategories != null)
		this.subCategories = subCategories.stream().map(sub -> new SubCategoryDTO(String.format("%s-%s", name,sub), sub)).collect(Collectors.toList());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SubCategoryDTO> getSubCategories() {
		return subCategories;
	}
	
	public boolean addSubCategory(String id, String name) {
		return this.getSubCategories().add(new SubCategoryDTO(id, name));
	}

	private final class SubCategoryDTO {
		
		private String id;
		private String name;

		public SubCategoryDTO() {
		}

		public SubCategoryDTO(String id, String name) {
			super();
			this.id = id;
			this.name = name;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

}
