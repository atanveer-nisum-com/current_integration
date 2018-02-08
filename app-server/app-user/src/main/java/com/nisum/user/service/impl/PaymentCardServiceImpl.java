package com.nisum.user.service.impl;

import com.nisum.common.util.DateUtils;
import com.nisum.user.dto.UserPaymentCardDTO;
import com.nisum.user.model.User;
import com.nisum.user.model.UserPaymentCard;
import com.nisum.user.repository.UserPaymentCardRepository;
import com.nisum.user.service.PaymentCardService;
import com.nisum.user.util.UserPaymentCardConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;




@Service("paymentCardService")
public class PaymentCardServiceImpl implements PaymentCardService  {


	@Autowired
	private UserPaymentCardRepository userPaymentCardRepository;

	@Autowired
	UserPaymentCardConverter userPaymentCardConverter;


	@Override
	public List<UserPaymentCard> getPaymentCardByUserId(Long userId) {
		return userPaymentCardRepository.findAllCardByUserId(userId);
	}

	@Override
	public UserPaymentCard getPaymentCardByNumber(Long cardNumber) {
		return userPaymentCardRepository.findCardByNumber(cardNumber);
	}

	@Override
	public UserPaymentCard save(UserPaymentCardDTO cardDto,Long userId) {
		if(cardDto.getCardNumber().toString().length() == 16) {
			UserPaymentCard existCard = userPaymentCardRepository.findCardByNumber(cardDto.getCardNumber());
			if(existCard == null) {
				updateDefaultCard(cardDto, userId);
				UserPaymentCard card = convertToEntity(cardDto, userId);
				card.setCreatedAt(DateUtils.getCurrentTime());

				UserPaymentCard userPaymentCard = userPaymentCardRepository.save(card);
				if(userPaymentCard==null)
					throw new EmptyResultDataAccessException(1);

				return	userPaymentCard;

			} else {
				throw new DuplicateKeyException("card already exists");
			}
		} else {
			throw new IllegalArgumentException("card number is not valid");
		}
	}

	@Override
	public UserPaymentCard update(UserPaymentCardDTO cardDto,Long userId,Long cardId) {	
		if(cardDto.getCardNumber().toString().length() != 16) {
			throw new IllegalArgumentException("card number is not valid");
		}
		UserPaymentCard updatedCard = userPaymentCardRepository.findOne(cardId);
		if(updatedCard!=null) {
			if(!updatedCard.getCardNumber().equals(cardDto.getCardNumber())) {
				throw new IllegalArgumentException("card number cannot be changed");
			}

			updateDefaultCard(cardDto, userId);
			//UserPaymentCard card = ETDUtils.toUserPaymentCard(cardDto,userId);
			UserPaymentCard card = convertToEntity(cardDto, userId);
			card.setId(cardId);
			card.setCreatedAt(updatedCard.getCreatedAt());

			UserPaymentCard userPaymentCard =  userPaymentCardRepository.save(card);
			if(userPaymentCard==null) {
				throw new EmptyResultDataAccessException(1);
			}
			return userPaymentCard;
		}else {
			throw new EmptyResultDataAccessException(1);
		}
	}


	@Override
	public void remove(Long paymentCardId) {
		if(userPaymentCardRepository.findOne(paymentCardId)==null) {
			throw new EmptyResultDataAccessException(1);
		}
		userPaymentCardRepository.delete(paymentCardId);

	}

	private UserPaymentCard convertToEntity(UserPaymentCardDTO cardDto, Long userId) {
		UserPaymentCard card = userPaymentCardConverter.convertToEntity(cardDto);
		User user = new User();
		user.setId(userId);
		card.setUser(user);
		card.setUpdatedAt(DateUtils.getCurrentTime());
		card.setIsDeleted((byte)0);
		return card;
	}

	private void updateDefaultCard(UserPaymentCardDTO cardDto, Long userId) {
		if(cardDto.getIsDefault() ==1) {
			UserPaymentCard previousCard = userPaymentCardRepository.findDefaultCardByUserId(userId);
			if(previousCard!=null) {
				previousCard.setIsDefault((byte)0);
				UserPaymentCard userPaymentCard = userPaymentCardRepository.save(previousCard);
				if(userPaymentCard == null) {
					throw new EmptyResultDataAccessException(1);
				}
			}

		}
	}

}
