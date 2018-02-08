package com.nisum.user.test.service;

import com.nisum.user.dto.UserPaymentCardDTO;
import com.nisum.user.model.UserPaymentCard;
import com.nisum.user.repository.UserPaymentCardRepository;
import com.nisum.user.service.PaymentCardService;
import com.nisum.user.util.UserPaymentCardConverter;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
/**
 * 
 * @author omussawir
 *
 */
public class PaymentCardServiceTest extends BaseServiceTest{
	
	@Autowired
	private PaymentCardService paymentCardService;
	
	@Autowired
	UserPaymentCardConverter userPaymentCardConverter;
	
	@MockBean
	private UserPaymentCardRepository userPaymentCardRepository;
	
	@Test 
	public void getPaymentCardByUserIDTest(){
		List<UserPaymentCard> userPaymentCards= new ArrayList<UserPaymentCard>();
		UserPaymentCard userPaymentCard = new UserPaymentCard();
		userPaymentCard.setId(1L);
		userPaymentCard.setCardNumber(1L);
		userPaymentCards.add(userPaymentCard);	
		Mockito.when(userPaymentCardRepository.findAllCardByUserId(Mockito.anyLong())).thenReturn(userPaymentCards);
		List<UserPaymentCard> userPaymentCardtem = paymentCardService.getPaymentCardByUserId(1L);
		assertEquals(userPaymentCardtem.get(0).getId(),userPaymentCards.get(0).getId() );
	}
	
	@Test 
	public void getPaymentCardByNumberTest(){
		UserPaymentCard userPaymentCard = new UserPaymentCard();
		userPaymentCard.setId(1L);
		userPaymentCard.setCardNumber(1L);	
		Mockito.when(userPaymentCardRepository.findCardByNumber(Mockito.anyLong())).thenReturn(userPaymentCard);
		UserPaymentCard userPaymentCardtem = paymentCardService.getPaymentCardByNumber(1L);
		assertEquals(userPaymentCardtem.getId(),userPaymentCard.getId() );
	}
	
	@Test 
	public void shouldSaveCard(){
		UserPaymentCard userPaymentCard = new UserPaymentCard();
		userPaymentCard.setId(1L);
		userPaymentCard.setCardNumber(1234567890123456L);
		UserPaymentCardDTO cardDto = new UserPaymentCardDTO();
		cardDto.setCardNumber(1234567890123456L);
		cardDto.setIsDefault((byte)1);
		cardDto.setPaymentTypeId(1L);
		UserPaymentCard userPreviousCard = new UserPaymentCard();
		userPreviousCard.setId(1L);
		userPreviousCard.setCardNumber(1234567890123456L);
		Mockito.when(userPaymentCardRepository.findCardByNumber(Mockito.anyLong())).thenReturn(null);
		Mockito.when(userPaymentCardRepository.save(Mockito.any(UserPaymentCard.class))).thenReturn(userPreviousCard);
		Mockito.when(userPaymentCardRepository.findDefaultCardByUserId(Mockito.anyLong())).thenReturn(userPaymentCard);
		UserPaymentCard userPaymentCardtem =paymentCardService.save(cardDto, 1L);
		assertEquals(userPaymentCard.getCardNumber(),userPaymentCardtem.getCardNumber());
	}

	/*
	 * save function should throw illegal argument exception if card number is less than 16
	 */
	@Test (expected = IllegalArgumentException.class)
	public void shouldThrowExceptionInvalidCard(){
		UserPaymentCardDTO cardDto = new UserPaymentCardDTO();
		cardDto.setCardNumber(123456789012345L);
		cardDto.setIsDefault((byte)1);
		cardDto.setPaymentTypeId(1L);
		paymentCardService.save(cardDto, 1L);
	}
	
	@Test (expected = DuplicateKeyException.class)
	public void shouldThrowExceptionIfCardExist(){
		UserPaymentCard userPaymentCard = new UserPaymentCard();
		userPaymentCard.setId(1L);
		userPaymentCard.setCardNumber(1234567890123456L);
		UserPaymentCardDTO cardDto = new UserPaymentCardDTO();
		cardDto.setCardNumber(1234567890123456L);
		cardDto.setIsDefault((byte)1);
		cardDto.setPaymentTypeId(1L);
		Mockito.when(userPaymentCardRepository.findCardByNumber(Mockito.anyLong())).thenReturn(userPaymentCard);
		paymentCardService.save(cardDto, 1L);
	}
	
	@Test 
	public void shouldUpdateCard(){
		UserPaymentCardDTO cardDto = new UserPaymentCardDTO();
		cardDto.setCardNumber(1234567890123456L);
		cardDto.setIsDefault((byte)1);
		cardDto.setPaymentTypeId(1L);
		UserPaymentCard userPaymentCard = new UserPaymentCard();
		userPaymentCard.setId(1L);
		userPaymentCard.setCardNumber(1234567890123456L);
		UserPaymentCard previousCard = new UserPaymentCard();
		previousCard.setId(1L);
		previousCard.setCardNumber(1234567890123456L);
		UserPaymentCard updatedCard = new UserPaymentCard();
		updatedCard.setId(1L);
		updatedCard.setCardNumber(1234567890123456L);
		Mockito.when(userPaymentCardRepository.findDefaultCardByUserId(Mockito.anyLong())).thenReturn(null);
		Mockito.when(userPaymentCardRepository.findOne(Mockito.anyLong())).thenReturn(updatedCard);
		Mockito.when(userPaymentCardRepository.save(Mockito.any(UserPaymentCard.class))).thenReturn(userPaymentCard);
		UserPaymentCard userPaymentCardtem =paymentCardService.update(cardDto, 1L, 1L);
		assertEquals(userPaymentCard.getCardNumber(),userPaymentCardtem.getCardNumber());	
	}
	
	@Test (expected = EmptyResultDataAccessException.class)
	public void shouldUpdateCardIfPreviousCardNotNull(){
		UserPaymentCardDTO cardDto = new UserPaymentCardDTO();
		cardDto.setCardNumber(1234567890123456L);
		cardDto.setIsDefault((byte)1);
		cardDto.setPaymentTypeId(1L);
		UserPaymentCard previousCard = new UserPaymentCard();
		previousCard.setId(1L);
		previousCard.setCardNumber(1234567890123456L);
		UserPaymentCard updatedCard = new UserPaymentCard();
		updatedCard.setId(1L);
		updatedCard.setCardNumber(1234567890123456L);
		UserPaymentCard userPaymentCard = new UserPaymentCard();
		userPaymentCard.setId(1L);
		userPaymentCard.setCardNumber(1234567890123456L);
		Mockito.when(userPaymentCardRepository.findDefaultCardByUserId(Mockito.anyLong())).thenReturn(updatedCard);
		Mockito.when(userPaymentCardRepository.save(Mockito.any(UserPaymentCard.class))).thenReturn(previousCard);	
		Mockito.when(userPaymentCardRepository.findOne(Mockito.anyLong())).thenReturn(updatedCard);	
		Mockito.when(userPaymentCardRepository.save(Mockito.any(UserPaymentCard.class))).thenReturn(userPaymentCard);	
	    UserPaymentCard userPaymentCardtem =paymentCardService.update(cardDto, 1L, 1L);	
		assertEquals(userPaymentCard.getCardNumber(),userPaymentCardtem.getCardNumber());
		Mockito.when(userPaymentCardRepository.save(Mockito.any(UserPaymentCard.class))).thenReturn(null);
		userPaymentCardtem = paymentCardService.update(cardDto, 1L, 1L);		
	}
	
	/*
	 * update function throws IllegalArgumentException if payment card number is not equals to 16
	 */
	@Test (expected = IllegalArgumentException.class)
	public void shouldThrowExceptionIfUpdateCardInvalid(){
		UserPaymentCardDTO cardDto = new UserPaymentCardDTO();
		cardDto.setCardNumber(12345678901234L);
		cardDto.setIsDefault((byte)1);
		cardDto.setPaymentTypeId(1L);
		paymentCardService.update(cardDto, 1L, 1L);
	}
	
	/*
	 * update function throws EmptyResultDataAccessException if payment card is not updated successfully
	 */
	@Test (expected = EmptyResultDataAccessException.class)
	public void shouldThrowExceptionIfCardNotUpdated(){
		UserPaymentCard updatedCard = new UserPaymentCard();
		updatedCard.setCardNumber(1234567890123456L);
		UserPaymentCardDTO updatedCardDTO = new UserPaymentCardDTO();
		updatedCardDTO.setCardNumber(1234567890123456L);
		Mockito.when(userPaymentCardRepository.findOne(Mockito.anyLong())).thenReturn(updatedCard);
		Mockito.when(userPaymentCardRepository.save(Mockito.any(UserPaymentCard.class))).thenReturn(null);
		paymentCardService.update(updatedCardDTO, 1L, 1L);
	}
	
	/*
	 * update function throws EmptyResultDataAccessException if payment card is not updated successfully
	 */
	
	@Test (expected = EmptyResultDataAccessException.class)
	public void shouldThrowExceptionIfCardNotExist(){
		UserPaymentCard updatedCard = new UserPaymentCard();
		updatedCard.setCardNumber(1234567890123456L);
		UserPaymentCardDTO updatedCardDTO = new UserPaymentCardDTO();
		updatedCardDTO.setCardNumber(1234567890123456L);
		Mockito.when(userPaymentCardRepository.findOne(Mockito.anyLong())).thenReturn(null);
		paymentCardService.update(updatedCardDTO, 1L, 1L);
	}
	/*
	 * update function throws IllegalArgumentException if card numbers are different
	 */
	@Test (expected = IllegalArgumentException.class)
	public void shouldThrowExceptionifCardNumberDifferent(){
		UserPaymentCard updatedCard = new UserPaymentCard();
		updatedCard.setCardNumber(1234567890123456L);
		UserPaymentCardDTO updatedCardDTO = new UserPaymentCardDTO();
		updatedCardDTO.setCardNumber(1224567890123456L);
		Mockito.when(userPaymentCardRepository.findOne(Mockito.anyLong())).thenReturn(updatedCard);
		paymentCardService.update(updatedCardDTO, 1L, 1L);
	}
	
	@Test 
	public void shouldRemove(){
		UserPaymentCard userPaymentCard = new UserPaymentCard();
		userPaymentCard.setCardNumber(1L);
		Mockito.when(userPaymentCardRepository.findOne(Mockito.anyLong())).thenReturn(userPaymentCard);
		paymentCardService.remove(1L);
	}

	@Test (expected = EmptyResultDataAccessException.class)
	public void RemoveShouldThrowException(){
		Mockito.when(userPaymentCardRepository.findOne(Mockito.anyLong())).thenReturn(null);
		paymentCardService.remove(1L);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void updateShouldThrowExceptionIfCardNumberInvalid(){
		UserPaymentCardDTO userPaymentCardDTO = new UserPaymentCardDTO();
		userPaymentCardDTO.setCardNumber(12345L);
		paymentCardService.update(userPaymentCardDTO, 1L, 1L);
	}
	
	@Test (expected = EmptyResultDataAccessException.class)
	public void saveShouldThrowEmptyResultDataAccessException(){
		UserPaymentCardDTO cardDTO = new UserPaymentCardDTO();
		cardDTO.setCardNumber(1234567890123456L);
		Mockito.when(userPaymentCardRepository.findCardByNumber(Mockito.anyLong())).thenReturn(null);
		Mockito.when(userPaymentCardRepository.save(Mockito.any(UserPaymentCard.class))).thenReturn(null);	
		paymentCardService.save(cardDTO, 1L);
	}
}
