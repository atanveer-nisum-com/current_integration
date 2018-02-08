package com.nisum.subscription.util;

import com.nisum.subscription.dto.EligibleProductDTO;
import com.nisum.subscription.model.EligibleProduct;

public class Converter {

	/**
	 * To EligibleProductDTO.
	 *
	 * @param product the EligibleProduct
	 * @return the EligibleProductDTO
	 */
	public static EligibleProductDTO toEligibleProductDto(EligibleProduct product) {
		EligibleProductDTO productDto = new EligibleProductDTO();
		if (product != null) {
			productDto.setProductID(product.getProductID());
			productDto.setClientID(product.getClientID());
			productDto.setName(product.getName());
			//productDto.setCampaignID(product.getCampaignID());
			productDto.setCategory(product.getCategory());
			productDto.setDelCharge(product.getDelCharge());
			productDto.setDelFrequency(product.getDelFrequency());
			productDto.setPrice(product.getPrice());
		}
		return productDto;
	}

}
