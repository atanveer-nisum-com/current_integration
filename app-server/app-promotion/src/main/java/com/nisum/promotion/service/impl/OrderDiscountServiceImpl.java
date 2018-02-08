/**
 * 
 */
package com.nisum.promotion.service.impl;

import com.nisum.promotion.model.OrderAmountDiscount;
import com.nisum.promotion.repository.OrderDiscountRepository;
import com.nisum.promotion.service.OrderDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Nisum Pakistan
 * The implementation class of Interface {@link OrderDiscountService}
 */
@Service("orderDiscountService")
public class OrderDiscountServiceImpl implements OrderDiscountService{

	@Autowired
	private OrderDiscountRepository orderDiscountRepository;
	
	public List<OrderAmountDiscount> getAllActiveDiscounts() {
		return orderDiscountRepository.getAllActiveDiscounts();
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = true)
	public Double getOrderDiscount(Double orderAmount) {
		List<OrderAmountDiscount> orderAmountDiscounts = orderDiscountRepository.getAllActiveDiscounts();
		Double orderDiscount = 0d;
		if (null != orderAmountDiscounts && orderAmountDiscounts.size() > 0) {
			for (OrderAmountDiscount oamd : orderAmountDiscounts) {
			   Double fromAmount = oamd.getFromAmount().doubleValue();
			   Double toAmount = oamd.getToAmount().doubleValue();
			   if (orderAmount >= fromAmount && orderAmount <= toAmount) {
				   orderDiscount = oamd.getDiscountValue().doubleValue();
			   }
			   //Max discountCase
			   else if (orderAmount >= fromAmount && toAmount == -1.0) {
				   orderDiscount = oamd.getDiscountValue().doubleValue();
			   }
			   else {
				   orderDiscount = oamd.getDiscountValue().doubleValue();
			   }
			}
			
		} else throw new EmptyResultDataAccessException(1);
		return orderDiscount;
	}

	
}
