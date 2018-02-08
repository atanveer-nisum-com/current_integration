package com.nisum.shop.controller;


import com.nisum.shop.dto.CheckoutDTO;
import com.nisum.shop.dto.RegistrationDTO;
import com.nisum.shop.dto.TransactionDTO;
import com.nisum.shop.model.OrderTransaction;
import com.nisum.shop.service.CheckoutService;
import com.nisum.shop.service.OrderService;
import com.nisum.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * The class {@code CheckoutController} includes method(s) 
 * for checkout a shopping cart and placing order
 *  
 * @author nisum pakistan
 *
 */

/** Request Path for Checkout Cart */
@RestController
@RequestMapping("shop")
public class CheckoutController extends BaseController{
	
	
	/** The order service. */
	@Autowired 
	private OrderService orderService;
	
	/** The checkout service. */
	@Autowired 
	private CheckoutService checkoutService;
	
	/** The user service. */
	@Autowired 
	private UserService userService;
	
	/** The state service. */
//	@Autowired 
//	private StateService stateService;
	
	/** The country service. */
//	@Autowired 
//	private CountryService countryService;
	
	/**
	 * Check out.
	 *
	 * @param userid the userid
	 * @return the checkout DTO
	 */
	@GetMapping("/checkout")
	public CheckoutDTO checkOut(@RequestHeader("userId") String userId) {
		return checkoutService.checkOut("".equals(userId)? null : Long.valueOf(userId));
	}
	
	/**
	 * Place order.
	 *
	 * @param transactionDTO the transaction DTO
	 * @return the boolean
	 */
	@PostMapping("/placeorder")
	public Boolean placeOrder(@RequestBody TransactionDTO transactionDTO) {
		transactionDTO.setOrderService(orderService);
		transactionDTO.setUserService(userService);
		
		OrderTransaction orderTransaction = transactionDTO.toOrderTransaction();
		
		orderTransaction = checkoutService.placeOrder(orderTransaction);
		if(orderTransaction != null && orderTransaction.getId() != null)
			return true;
		else 
			return false;
	}
	
	/**
	 * Save user.
	 *
	 * @param registrationDTO the registration DTO
	 * @return the boolean
	 */
	@PostMapping("/saveuser")
	public Boolean saveUser(@RequestBody RegistrationDTO registrationDTO) {
		//set services to the DTO
//		registrationDTO.setCountryService(countryService);
//		registrationDTO.setStateService(stateService);
//		registrationDTO.setUserService(userService);
//		
//		//update the user with the given information
//		User userToUpdate = registrationDTO.toUser();
		
//		userService.update(userToUpdate);
		return userService.update(registrationDTO);
		
//		return true;
	}
}
