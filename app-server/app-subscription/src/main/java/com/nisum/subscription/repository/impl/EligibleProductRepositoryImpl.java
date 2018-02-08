package com.nisum.subscription.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.nisum.subscription.model.EligibleProduct;
import com.nisum.subscription.repository.EligibleProductRepository;

@Repository("eligibleproductsCassandraRepository")
public class EligibleProductRepositoryImpl implements EligibleProductRepository {
	@Autowired
	private CassandraOperations cassandraTemplate; 
	@Override
	public EligibleProduct findByProductIDAndClientID(String productID, String clientID) {
		String cql = String.format("select * from eligibleproducts where productid='%s' and clientid='%s'", productID,clientID);
		return this.cassandraTemplate.selectOne(cql, EligibleProduct.class);
	}

	@Override
	public List<EligibleProduct> findByClientID(String clientID, Pageable pageable) {
		String cql = String.format("select * from eligibleproducts where clientid='%s'", clientID);
		return this.cassandraTemplate.select(cql, EligibleProduct.class);
	}

	@Override
	public EligibleProduct findByCampaignIDAndClientID(String campaignID, String clientID) {
		String cql = String.format("select * from eligibleproducts where campaignid='%s' and clientid='%s'", campaignID,clientID);
		return this.cassandraTemplate.selectOne(cql, EligibleProduct.class);
	}
	
	@Override
	public List<EligibleProduct> findProductsByCategory(String category, Pageable pageable) {
		String cql = String.format("select from eligibleproducts where category='%s'", category);
		return this.cassandraTemplate.select(cql, EligibleProduct.class);
	}
	
	@Override
	public List<EligibleProduct> findProductsBydeliveryFrequency(String deliveryFrequency, Pageable pageable) {
		String cql = String.format("select from eligibleproducts where delFrequency='%s'", deliveryFrequency);
		return this.cassandraTemplate.select(cql, EligibleProduct.class);
	}

}
