package com.nisum.subscription.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.nisum.subscription.model.EligibleProduct;

public interface EligibleProductRepository {
	
	EligibleProduct findByProductIDAndClientID(String productID, String clientID);
	
	List<EligibleProduct> findByClientID(String clientID,Pageable pageable);
	
	EligibleProduct findByCampaignIDAndClientID(String campaignID, String clientID);
	
	List<EligibleProduct> findProductsByCategory(String category, Pageable pageable);
	
	List<EligibleProduct> findProductsBydeliveryFrequency(String deliveryFrequency, Pageable pageable);

}
