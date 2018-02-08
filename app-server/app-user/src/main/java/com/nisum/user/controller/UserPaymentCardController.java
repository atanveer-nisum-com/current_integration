package com.nisum.user.controller;

import com.nisum.common.constant.ErrorLevel;
import com.nisum.common.exception.rest.RestException;
import com.nisum.user.dto.UserPaymentCardDTO;
import com.nisum.user.model.UserPaymentCard;
import com.nisum.user.service.PaymentCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.concurrent.ExecutorService;

@RestController
@RequestMapping("users/cards")
public class UserPaymentCardController extends BaseController {

	@Autowired
	private PaymentCardService paymentCardService;
	
	@Autowired
	ExecutorService executorService;
	
	@GetMapping
	public DeferredResult<List<UserPaymentCard>> getPaymentCardByUserId(@RequestHeader("userId") String userId) {
		DeferredResult<List<UserPaymentCard>> userPaymentCardResult = new DeferredResult<>();
		executorService.execute(() -> {
			List<UserPaymentCard> paymentCard = paymentCardService.getPaymentCardByUserId(Long.valueOf(userId));
			if (paymentCard == null || paymentCard.size() == 0) {
				userPaymentCardResult.setErrorResult(new RestException(HttpStatus.NOT_FOUND, "Payment Card List Is Empty", ErrorLevel.ERROR));
			}else {
				userPaymentCardResult.setResult(paymentCard);
			}
		});
		return userPaymentCardResult;
	}
	
	@PostMapping
	public DeferredResult<UserPaymentCard> savePaymentCard(@RequestBody UserPaymentCardDTO cardDto,@RequestHeader("userId") String userId) {
		DeferredResult<UserPaymentCard> userPaymentCardResult = new DeferredResult<>();
		executorService.execute(() -> {
			try {
				UserPaymentCard paymentCard = paymentCardService.save(cardDto,Long.valueOf(userId));
				userPaymentCardResult.setResult(paymentCard);
				
			} catch(IllegalArgumentException re) {
				userPaymentCardResult.setErrorResult(new RestException(HttpStatus.BAD_REQUEST, re.getMessage(), ErrorLevel.ERROR));

			} catch(DuplicateKeyException re) {
				userPaymentCardResult.setErrorResult(new RestException(HttpStatus.CONFLICT, re.getMessage(), ErrorLevel.ERROR));
			
			} catch(EmptyResultDataAccessException re) {
				userPaymentCardResult.setErrorResult(new RestException(HttpStatus.INTERNAL_SERVER_ERROR, "Couldnot save your card", ErrorLevel.ERROR));
			}
			
			

		});
		return userPaymentCardResult;
	}
	
	@PutMapping("/{cardId}")
	public DeferredResult<UserPaymentCard> updatePaymentCard(@PathVariable Long cardId, @RequestBody UserPaymentCardDTO cardDto,@RequestHeader("userId") String userId) {
		DeferredResult<UserPaymentCard> userPaymentCardResult = new DeferredResult<>();
		executorService.execute(() -> {
			try { 
				UserPaymentCard paymentCard = paymentCardService.update(cardDto,Long.valueOf(userId),cardId);
				userPaymentCardResult.setResult(paymentCard);
				
			} catch(IllegalArgumentException re) {
				userPaymentCardResult.setErrorResult(new RestException(HttpStatus.BAD_REQUEST, re.getMessage(), ErrorLevel.ERROR));

			}  catch(EmptyResultDataAccessException re) {
				userPaymentCardResult.setErrorResult(new RestException(HttpStatus.INTERNAL_SERVER_ERROR, "Couldnot update your card", ErrorLevel.ERROR));
			}
		});
		return userPaymentCardResult;
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{cardId}")
	public void removePaymentCard(@PathVariable Long cardId,@RequestHeader("userId") String userId) {
			try {
				 paymentCardService.remove(cardId);
			} catch (EmptyResultDataAccessException e) {
				throw new RestException(HttpStatus.NOT_FOUND, "card not found", ErrorLevel.ERROR);
			}
	}
	
}
