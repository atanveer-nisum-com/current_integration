/**
 * 
 */
package com.nisum.promotion.controller;

import com.nisum.common.constant.ErrorLevel;
import com.nisum.common.exception.rest.RestException;
import com.nisum.promotion.dto.CategoryPromotion;
import com.nisum.promotion.dto.ProductCategoryPromotion;
import com.nisum.promotion.dto.ProductPromotion;
import com.nisum.promotion.service.CategoryDiscountService;
import com.nisum.promotion.service.OrderDiscountService;
import com.nisum.promotion.service.ProductDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;

/**
 * @author Nisum Pakistan
 * Expose Rest Methods for discount 
 * Spring Controller class act as Rest Service
 */
@RestController
@RequestMapping("discount")
public class DiscountController  {

	@Autowired
	private OrderDiscountService orderDiscountService;
	@Autowired 
	private ProductDiscountService productDiscountService;
	@Autowired
	private CategoryDiscountService categoryDiscountService;
	
	@Autowired
	private ExecutorService executorService;
	
	/**
	 * Get the order discount
	 * @param orderAmount
	 * @return Double
	 */
	@GetMapping("/order/{orderAmount}")
	public DeferredResult<Double> getOrderDiscount(@PathVariable(value="orderAmount") Double orderAmount) {
		DeferredResult<Double> deferredResult = new DeferredResult<>();
		executorService.execute(()->{
			try {
				deferredResult.setResult(orderDiscountService.getOrderDiscount(orderAmount));
			} catch (EmptyResultDataAccessException e) {
				deferredResult.setErrorResult(new RestException(HttpStatus.NOT_FOUND, "No order discount found", ErrorLevel.ERROR));
			}
		});
		return deferredResult;
	}
	
	/**
	 * 
	 * @param productIds
	 * @return {@link List<?>}
	 */
	@GetMapping("/products/{productIds}")
	public DeferredResult<List<ProductPromotion>> getDisountByProducts(@PathVariable String [] productIds){
		DeferredResult<List<ProductPromotion>> deferredResult = new DeferredResult<>();
		executorService.execute(() -> {
			try {
				deferredResult.setResult(productDiscountService.getDiscountByProducts(productIds));
			} catch (EmptyResultDataAccessException e) {
				deferredResult.setErrorResult(new RestException(HttpStatus.NOT_FOUND, "Promotion not found for products", ErrorLevel.ERROR));
			}
		});
		 return deferredResult;
	}
	
	/**
	 * Get discounts by product and categories
	 * 
	 * @param productIds
	 * @return {@link List<ProductCatgoryPromotion>}
	 */
	@GetMapping("/products/categories/{productIds}")
	public DeferredResult<List<ProductCategoryPromotion>> getDiscountByProductAndPromotion(@PathVariable Set<String> productIds) {
		DeferredResult<List<ProductCategoryPromotion>> deferredResult = new DeferredResult<>();
		executorService.execute(()->{
			try {
				String[] ids = productIds.parallelStream().toArray(String[]::new);
				deferredResult.setResult(productDiscountService.getDiscountByProductAndCategories(ids));
			} catch (EmptyResultDataAccessException e) {
				deferredResult.setErrorResult(new RestException(HttpStatus.NOT_FOUND, "No promotions found.", ErrorLevel.ERROR));
			}
		});
		return deferredResult;
	}
	
	/**
	 * 
	 * @param categoryIds
	 * @return
	 */
	@GetMapping("/categories/{categoryIds}")
	public DeferredResult<List<CategoryPromotion>> getDiscountByCategories(@PathVariable Set<String> categoryIds){
		DeferredResult<List<CategoryPromotion>> deferredResult = new DeferredResult<>();
		executorService.execute(()->{
			try {
				deferredResult.setResult(categoryDiscountService.getDiscountByCategories(categoryIds));
			} catch (EmptyResultDataAccessException e) {
				deferredResult.setErrorResult(new RestException(HttpStatus.NOT_FOUND, "Promotion not found against category", ErrorLevel.ERROR));
			}
		});
		return deferredResult;
	}
}
