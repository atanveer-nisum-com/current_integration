/**
 * 
 */
package com.nisum.user.controller;

import com.nisum.common.constant.ErrorLevel;
import com.nisum.common.exception.rest.RestException;
import com.nisum.user.dto.StoreDTO;
import com.nisum.user.service.StoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * The StoreController which uses storeService
 * <p>
 * Contains WebMethods for user-store preference
 * </p>
 * @author aali
 *
 */
@RestController
@RequestMapping("stores")
public class StoreController extends BaseController {
	
	@Autowired
	private StoreService storeService;
	@Autowired
	private ExecutorService executorService;
	
	@GetMapping
	public DeferredResult<Page<StoreDTO>> getStores() {
		DeferredResult<Page<StoreDTO>> storeResult = new DeferredResult<>();
		executorService.execute(() -> {
			
			try {
					Page<StoreDTO> storePage = storeService.getStores();
					storeResult.setResult(storePage);
				
			} catch(EmptyResultDataAccessException e) {
				storeResult.setErrorResult(new RestException(HttpStatus.NOT_FOUND, "No store found", ErrorLevel.ERROR));
			}
		});
		return storeResult;
	}
	
	@GetMapping("/{storeid}")
	public DeferredResult<StoreDTO> getStoreById(@PathVariable Long storeid) {
		DeferredResult<StoreDTO> storeResult = new DeferredResult<>();
		executorService.execute(() -> {
			StoreDTO storeDTO;
			try {
					storeDTO = storeService.getStoreById(storeid);
					storeResult.setResult(storeDTO);
				
			} catch(EmptyResultDataAccessException e) {
				storeResult.setErrorResult(new RestException(HttpStatus.NOT_FOUND, "No store found", ErrorLevel.ERROR));
			}
		});
		return storeResult;
	}
	
	/**
	 * invokes service method getStoreByZipcode of storeService
	 * 
	 * @param zipcode
	 *            the zipcode
	 * @param pageable
	 *            the pageable
	 * @return stores corresponding to zipcode
	 */
	@GetMapping(params="zipcode")
	public DeferredResult<Page<StoreDTO>> getStoreByZipcode(@RequestParam Long zipcode,
												   @PageableDefault(size = PAGE_SIZE_12) Pageable pageable) {
		DeferredResult<Page<StoreDTO>> storeResult = new DeferredResult<>();
		executorService.execute(() -> {
			try {
					Page<StoreDTO> storePage = storeService.getStoreByZipcode(zipcode, pageable);
					storeResult.setResult(storePage);
				
			} catch(EmptyResultDataAccessException e) {
				storeResult.setErrorResult(new RestException(HttpStatus.NOT_FOUND, "No store found", ErrorLevel.ERROR));
			}
		});
		return storeResult;
		
		
	}
	/**
	 * invokes service method getStoreByUserId
	 * @param userid
	 * 			  the userid
	 * @return store corresponding to that userid
	 */
	@GetMapping(headers = "userId")
	public DeferredResult<StoreDTO> getStoreByUserId(@RequestHeader("userId") Long userid) {
		DeferredResult<StoreDTO> storeResult = new DeferredResult<>();
		executorService.execute(() -> {
			StoreDTO storeDTO;
			try {
					storeDTO = storeService.getStoreByUserId(userid);
					storeResult.setResult(storeDTO);
				
			} catch(EmptyResultDataAccessException e) {
				storeResult.setErrorResult(new RestException(HttpStatus.NOT_FOUND, "No store found", ErrorLevel.ERROR));
			}
		});
		return storeResult;
		
	}
	/**
	 * invokes saveStore method of storeService
	 * 
	 * @param userid
	 *            the userid
	 * @param storeId
	 *            the storeId
	 */
	@PostMapping
	public DeferredResult<StoreDTO> saveStore(@RequestHeader("userId") Long userid,
									 @RequestBody Map<String, Long> storeId) {
		DeferredResult<StoreDTO> storeResult = new DeferredResult<>();
		executorService.execute(() -> {
			StoreDTO storeDTO;
			try {
					storeDTO = storeService.saveStore(userid, storeId.get("storeId"));
					storeResult.setResult(storeDTO);
				
			} catch(DataIntegrityViolationException e) {
				storeResult.setErrorResult(new RestException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(),
						ErrorLevel.ERROR));
			}
		});
		return storeResult;
	}

}
