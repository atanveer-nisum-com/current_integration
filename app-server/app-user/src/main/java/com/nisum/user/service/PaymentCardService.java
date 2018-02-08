package com.nisum.user.service;

import com.nisum.user.dto.UserPaymentCardDTO;
import com.nisum.user.model.UserPaymentCard;

import java.util.List;

public interface PaymentCardService {

	public List<UserPaymentCard> getPaymentCardByUserId(Long userId);
	
	public UserPaymentCard getPaymentCardByNumber(Long cardNumber);
	
	public UserPaymentCard save(UserPaymentCardDTO cardDto,Long userId);
	
	public UserPaymentCard update(UserPaymentCardDTO cardDto,Long userId,Long cardId);
	
	public void remove(Long paymentCardId);
	
}
