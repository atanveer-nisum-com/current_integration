package com.nisum.user.api.dto;

import java.io.Serializable;

/**
 * The Class ItemImageDTO.
 */
public class ItemImageDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The image path. */
	private String imagePath;

	public ItemImageDTO() {
	}

	public ItemImageDTO(String imagePath) {
		super();
		this.imagePath = imagePath;
	}

	/**
	 * Gets the image path.
	 *
	 * @return the image path
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * Sets the image path.
	 *
	 * @param imagePath
	 *            the new image path
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}