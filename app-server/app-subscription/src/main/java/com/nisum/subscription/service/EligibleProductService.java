package com.nisum.subscription.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.nisum.subscription.dto.EligibleProductDTO;
import com.nisum.subscription.model.EligibleProduct;

/**
 * The Interface EligibleProductService.
 */
public interface EligibleProductService {
	
	/**
	 * Gets the product by productID and clientID.
	 * 
	 *@param productID the productID
	 *@param clientId the clientID
	 * @return product
	 */
	public EligibleProductDTO getProductByIDAndClientID(String productId, String clientID);
	
	/**
	 * Gets the product by clientID.
	 *
	 *@param clientId the clientID
	 * @param pageable the pageable
	 * @return list of products
	 */
	public List<EligibleProductDTO> getProductByClientID(String clientID,Pageable pageable);
	
	/**
	 * Gets the product by clientID and campaignID.
	 * 
	 *@param campaignID the campaignID
	 *@param clientId the clientID
	 * @return product
	 */
	public EligibleProductDTO getProductByCampaignIDAndClientID(String campaignID, String clientID);
	
	public List<EligibleProductDTO> getProductsByCategory(String category, Pageable pageable);
	
	public List<EligibleProductDTO> getProductsByDeliveryFrequency(String delFrequency, Pageable pageable);
	

}
