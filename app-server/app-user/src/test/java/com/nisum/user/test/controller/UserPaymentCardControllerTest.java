package com.nisum.user.test.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.common.exception.rest.RestException;
import com.nisum.user.controller.UserPaymentCardController;
import com.nisum.user.dto.UserPaymentCardDTO;
import com.nisum.user.model.User;
import com.nisum.user.model.UserPaymentCard;
import com.nisum.user.service.PaymentCardService;
import com.nisum.user.test.mock.models.MockModelFactory;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * The Class UserPaymentCardControllerTest.
 */
@WebMvcTest(value = UserPaymentCardController.class, secure = false)
public class UserPaymentCardControllerTest extends BaseControllerTest{

	UserPaymentCard mockUserPaymentCard = new UserPaymentCard();
	
	private final String  USERS_URL = "/users/cards";
	private final String  UPDATE_USER_URL = USERS_URL+"/123313";
	
	/**
	 * The MockBean PaymentCardService .
	 */
	
	@MockBean 
	private PaymentCardService paymentCardService;
	
	/**
	 * get PaymentCardByUserId.
	 *
	 * @param userId
	 *            the user id
	 
	 * @return the List of UserPaymentCard
	 */
	@Test
	public void getPaymentCardByUserId() throws Exception {
		
		mockUserPaymentCard.setId(1L);
		User user = new User();
		user.setId(1232l);
		mockUserPaymentCard.setUser(user);
		mockUserPaymentCard.setCardNumber(112L);
		List<UserPaymentCard> userPaymentList=new ArrayList<UserPaymentCard>();
		userPaymentList.add(mockUserPaymentCard);
		Mockito.when(paymentCardService.getPaymentCardByUserId(Mockito.anyLong())).thenReturn(userPaymentList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(USERS_URL)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.header("userId", "2");
		
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		mvc.perform(asyncDispatch(result)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].cardNumber").value(112));		        
	}
	
	/**
	 * get Payment Card By UserId with empty paymentCard
	 * @param userId
	 *        
	 * @return the Exception
	 */
	
	@Test
	public void getPaymentCardShouldThrowException() {
		
		Mockito.when(paymentCardService.getPaymentCardByUserId(Mockito.anyLong())).thenReturn(null);
	
		try {
			 RequestBuilder requestBuilder = MockMvcRequestBuilders
					.get(USERS_URL)
					.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON)
					.header("userId", "2");	
			 MvcResult result =  mvc.perform(requestBuilder).andExpect(request().asyncStarted()).andExpect(request().asyncResult(instanceOf(RestException.class))).andReturn();
		     mvc.perform(asyncDispatch(result)).andExpect(status().isNotFound());
			 
		} catch (Exception e) {
			
			if (e.getCause() instanceof RestException) {
				RestException re = (RestException) e.getCause();
			    assertEquals(re.getHttpStatus() ,  HttpStatus.NOT_FOUND);
			    assertEquals(re.getMessage() , "Payment Card List Is Empty");
			}
		}
	}
	
	/**
	 * save  UserPaymentCard.
	 *  @param UserPaymentCardDto
	 *           
	 * @return  UserPaymentCard
	 */
	
	@Test
	public void saveUserPaymentCard() throws Exception{
		
		User user = MockModelFactory.getMockUser();
		mockUserPaymentCard.setCardNumber(12234324l);
		mockUserPaymentCard.setId(1233l);
		mockUserPaymentCard.setIsDefault((byte)0);
		mockUserPaymentCard.setUser(user);
		
		Mockito.when(paymentCardService.save(Mockito.anyObject(),Mockito.anyLong())).thenReturn(mockUserPaymentCard);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(USERS_URL)
				.accept(MediaType.APPLICATION_JSON).content(getSavePaymentFormDTO())
				.header("userId", "1")
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		mvc.perform(asyncDispatch(result))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1233L))
		        .andExpect(jsonPath("$.cardNumber").isNumber())
		        .andExpect(jsonPath("$.isDefault").value(0));
		        
	}
	
	/**
	 * save  UserPaymentCard.
	 *  @param UserPaymentCardDto
	 *           
	 * @throw  IllegalArgumentException when card number is not valid
	 */
	
	@Test
	public void savePaymentCardShouldThrowIllegalArgumentException()  {
		
		Mockito.when(paymentCardService.save(Mockito.anyObject(),Mockito.anyLong()))
				.thenThrow(new IllegalArgumentException());
		try {
			
			 RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(USERS_URL)
				.accept(MediaType.APPLICATION_JSON).content(getSavePaymentFormDTO())
				.header("userId", "1")
				.contentType(MediaType.APPLICATION_JSON);
		
		     MvcResult  result =  mvc.perform(requestBuilder).andExpect(request().asyncStarted()).andExpect(request().asyncResult(instanceOf(RestException.class))).andReturn();
		      mvc.perform(asyncDispatch(result)).andExpect(status().isBadRequest());
			 
		} catch (Exception e) {
			
			if (e.getCause() instanceof RestException) {
				RestException re = (RestException) e.getCause();
			    assertEquals(re.getHttpStatus(),  HttpStatus.BAD_REQUEST);
			    //assertEquals(re.getMessage() , null);
			}
		}  
	}
	
	/**
	 * save  UserPaymentCard.
	 *  @param UserPaymentCardDto
	 *           
	 * @return  DuplicateKeyException when Duplicate Card Number is added
	 */
	
	@Test
	public void savePaymentCardShouldThrowDuplicateKeyException() {
		
		Mockito.when(paymentCardService.save(Mockito.anyObject(),Mockito.anyLong()))
		 .thenThrow(new DuplicateKeyException("card already exists"));
      try {	
    	  
	        RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(USERS_URL)
				.accept(MediaType.APPLICATION_JSON).content(getSavePaymentFormDTO())
				.header("userId", "1")
				.contentType(MediaType.APPLICATION_JSON);
	         MvcResult result;		 
	        	 result =  mvc.perform(requestBuilder).andExpect(request().asyncStarted()).andExpect(request().asyncResult(instanceOf(RestException.class))).andReturn();
	             mvc.perform(asyncDispatch(result)).andExpect(status().isConflict());
        	 
		} catch (Exception e) {
			
			if (e.getCause() instanceof RestException) {
				 RestException re = (RestException) e.getCause();
				 assertEquals(re.getHttpStatus(),  HttpStatus.CONFLICT);
				 assertEquals(re.getMessage() , "card already exists");
			}
		}
	}
	
	/**
	 * save  UserPaymentCard.
	 *  @param UserPaymentCardDto
	 *           
	 * @throw  EmptyResultDataAccessException when UserPaymentCard Is Null
	 */
	
	@Test
	public void savePaymentCardShouldThrowEmptyResultDataAccessException() {
		Mockito.when(paymentCardService.save(Mockito.anyObject(),Mockito.anyLong()))
		 .thenThrow(new EmptyResultDataAccessException(1));
      try {	
    	  
	        RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(USERS_URL)
				.accept(MediaType.APPLICATION_JSON).content(getSavePaymentFormDTO())
				.header("userId", "1")
				.contentType(MediaType.APPLICATION_JSON);
	        
	        MvcResult result =  mvc.perform(requestBuilder).andExpect(request().asyncStarted()).andExpect(request().asyncResult(instanceOf(RestException.class))).andReturn();
	             mvc.perform(asyncDispatch(result)).andExpect(status().isInternalServerError());
        	 
		} catch (Exception e) {
			
			if (e.getCause() instanceof RestException) {
				 RestException re = (RestException) e.getCause();
				 assertEquals(re.getHttpStatus(),  HttpStatus.INTERNAL_SERVER_ERROR);
				 assertEquals(re.getMessage(),"Couldnot save your card");
			}
		}
	}
	
	/**
	 * Update userPaymentCard.
	 *
	 * @param cardId
	 * @param userPaymentCardDto          
	 * @return the UserPaymentCard
	 */
	
	@Test
	public void updatePaymentCard() throws Exception{
		
		User user = MockModelFactory.getMockUser();
		mockUserPaymentCard.setCardNumber(12234324l);
		mockUserPaymentCard.setId(1233l);
		mockUserPaymentCard.setIsDefault((byte)0);
		mockUserPaymentCard.setUser(user);
		
		Mockito.when(paymentCardService.update(Mockito.anyObject(),Mockito.anyLong(),Mockito.anyLong())).thenReturn(mockUserPaymentCard);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put(UPDATE_USER_URL)
				.accept(MediaType.APPLICATION_JSON).content(getSavePaymentFormDTO())
				.header("userId", "2")
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		mvc.perform(asyncDispatch(result))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1233L))
				.andExpect(jsonPath("$.cardNumber").isNumber())
		        .andExpect(jsonPath("$.isDefault").value(0))
		        .andReturn();
	}
	
	/**
	 * Update userPaymentCard.
	 *
	 * @param cardId
	 * @param userPaymentCardDto          
	 * @throw the IllegalArgumentException when card number is not valid
	 */
	
	@Test
	public void updatePaymentCardShouldThrowIllegalArgumentException () {
		
		Mockito.when(paymentCardService.update(Mockito.anyObject(),Mockito.anyLong(),Mockito.anyLong()))
				.thenThrow(new IllegalArgumentException());
		try {
			
			  RequestBuilder requestBuilder = MockMvcRequestBuilders
					.put(UPDATE_USER_URL)
					.accept(MediaType.APPLICATION_JSON).content(getSavePaymentFormDTO())
					.header("userId", "1")
					.contentType(MediaType.APPLICATION_JSON);
			  MvcResult result =  mvc.perform(requestBuilder).andExpect(request().asyncStarted()).andExpect(request().asyncResult(instanceOf(RestException.class))).andReturn();
				     mvc.perform(asyncDispatch(result)).andExpect(status().isBadRequest());
			 
		} catch (Exception e) {
			
			if (e.getCause() instanceof RestException) {
				 RestException re = (RestException) e.getCause();
				 assertEquals(re.getHttpStatus(),  HttpStatus.BAD_REQUEST);
			}
		}  
	}
	
	/**
	 * Update userPaymentCard.
	 *
	 * @param cardId
	 * @param userPaymentCardDto          
	 * @throw the EmptyResultDataAccessException when user payment card is null
	 */
	
	@Test
	public void updatePaymentCardShouldThrowEmptyResultDataAccessException () {
		
		Mockito.when(paymentCardService.update(Mockito.anyObject(),Mockito.anyLong(),Mockito.anyLong()))
				.thenThrow(new EmptyResultDataAccessException(1));
		try {
			
			  RequestBuilder requestBuilder = MockMvcRequestBuilders
					.put(UPDATE_USER_URL)
					.accept(MediaType.APPLICATION_JSON).content(getSavePaymentFormDTO())
					.header("userId", "1")
					.contentType(MediaType.APPLICATION_JSON);
			  MvcResult result =  mvc.perform(requestBuilder).andExpect(request().asyncStarted()).andExpect(request().asyncResult(instanceOf(RestException.class))).andReturn();
				     mvc.perform(asyncDispatch(result)).andExpect(status().isInternalServerError()).andReturn();
			 
		} catch (Exception e) {
			
			if (e.getCause() instanceof RestException) {
				 RestException re = (RestException) e.getCause();
				 assertEquals(re.getHttpStatus(),  HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}  
	}
	/*
	 * Should Throw EmptyResultDataAccessException
	 */
	@Test
	public void removePaymentCardShouldThrowException() {
		
		doThrow(new EmptyResultDataAccessException(1)).when(paymentCardService).remove(Mockito.anyLong());
		
	    try { 
	    	
	    	 RequestBuilder requestBuilder = MockMvcRequestBuilders
					.delete(UPDATE_USER_URL)
					.accept(MediaType.APPLICATION_JSON)
					.header("userId", "1")
					.contentType(MediaType.APPLICATION_JSON);
	    	 mvc.perform(requestBuilder).andReturn();
				  
		 }catch(Exception e) {
			 
			 if (e.getCause() instanceof RestException) {
					RestException re = (RestException) e.getCause();
					assertEquals(re.getHttpStatus() ,  HttpStatus.NOT_FOUND);
					assertEquals(re.getMessage() , "card not found");
				}
		 }
			   
	}
	
	@Test
	public void removePaymentCard() throws Exception {

			  RequestBuilder requestBuilder = MockMvcRequestBuilders
					.delete(UPDATE_USER_URL)
					.accept(MediaType.APPLICATION_JSON)
					.header("userId", "1")
					.contentType(MediaType.APPLICATION_JSON);
			  
			  mvc.perform(requestBuilder).andExpect(status().isNoContent());
		   
	}
	
	private String getSavePaymentFormDTO() throws JsonProcessingException {
		
		UserPaymentCardDTO userPaymentCardDTO = new UserPaymentCardDTO();
		userPaymentCardDTO.setCardNumber(133333l);
		userPaymentCardDTO.setPaymentTypeId(2323l);
		return new ObjectMapper()
	            .writeValueAsString(userPaymentCardDTO);
	}

}
