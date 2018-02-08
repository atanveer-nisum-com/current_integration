//package com.nisum.shop.service.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import com.nisum.common.constant.CommonEndPointConstant;
//import com.nisum.shop.model.State;
//import com.nisum.shop.service.StateService;
//
//@Service
//public class StateServiceImpl implements StateService{
//
//	@Autowired
//	private RestTemplate restClient;
//	
//	@Override
//	public State findStateById(Long id) {
//		State state = restClient.getForObject(CommonEndPointConstant.STATE_FIND_BY_ID, State.class);
//		return state;
//	}
//
//}
