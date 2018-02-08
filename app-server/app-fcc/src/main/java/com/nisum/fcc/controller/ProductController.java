package com.nisum.fcc.controller;

import com.nisum.common.constant.ErrorLevel;
import com.nisum.common.exception.rest.RestException;
import com.nisum.fcc.dto.ProductDTO;
import com.nisum.fcc.service.ProductService;

import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;


/**
 * The Class ProductController.
 */
@RestController
@RequestMapping("products")
public class ProductController extends BaseController {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	/** The product service. */
	@Autowired
	private ProductService productCassandraService;
	
	@Autowired
	private ExecutorService executorService;

	/**
	 * List.
	 *
	 * @param pageable the pageable
	 * @return the deferred result
	 */
	@GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public DeferredResult<Page<ProductDTO>> list(@PageableDefault(size = PAGE_SIZE_21) Pageable pageable) {
		DeferredResult<Page<ProductDTO>> defResult = new DeferredResult<>();
		
		Runnable task = (() -> {
			try {
			List<ProductDTO> products = productCassandraService.getAllByPage(pageable);
				if (products != null && products.size() >= 0) {
					Page<ProductDTO> productDTOs = new PageImpl<ProductDTO>(products);
					defResult.setResult(productDTOs);
				}
			} catch (Exception e) {
				defResult.setErrorResult(new RestException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occured in getting products.", ErrorLevel.ERROR));
			}
		});
		
		executorService.execute(task);
		
		return defResult;
	}
	
	
	@GetMapping(value="/items/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public DeferredResult<ProductDTO> findProductById(@PathVariable UUID id) {
		DeferredResult<ProductDTO> defResult = new DeferredResult<>();
		
		Runnable task = (() -> {
			ProductDTO productDTO = productCassandraService.getProductById(id,null);
			if(productDTO == null){
				defResult.setErrorResult(new RestException(HttpStatus.NO_CONTENT, "Product is empty", ErrorLevel.ERROR));
			}else{
				defResult.setResult(productDTO);
			}
		});
		
		executorService.execute(task);
		
		return defResult;
	}


	/**
	 * Find item by id.
	 *
	 * @param name the id
	 * @return the product
	 */
	@GetMapping(value="/{name}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public DeferredResult<ProductDTO> findProductByNameAndCode(@PathVariable String name) {
		DeferredResult<ProductDTO> defResult = new DeferredResult<ProductDTO>();
		
		Runnable task = () -> {
			ProductDTO product = productCassandraService.findByUniqueIdentifier(name);
			if (product == null || product.getId() == null) {
				defResult.setErrorResult(new RestException(HttpStatus.NOT_FOUND, "Product does not Found", ErrorLevel.ERROR));
			}else {
				defResult.setResult(product);
			}
		};
		
		executorService.execute(task);
		
		return defResult;
	}
	
	
	
	/**
	 * Search item.
	 *
	 * @param categoryId the category id
	 * @param pageable the pageable
	 * @return the page
	 */
	@GetMapping(value="/categories/{categoryId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public DeferredResult<Page<ProductDTO>> getProductByCategory(@PathVariable Object categoryId,@PageableDefault(size=PAGE_SIZE_21) Pageable pageable) {
		DeferredResult<Page<ProductDTO>> defResult = new DeferredResult<>();
		
		Runnable task = (() -> {
			Page<ProductDTO> products = productCassandraService.getProductByCategory(categoryId,pageable);
			if(products == null || products.getTotalElements() == 0){
				defResult.setErrorResult(new RestException(HttpStatus.NO_CONTENT, "Productlist is empty", ErrorLevel.ERROR));
			}else{
				defResult.setResult(products);	
			}
		});
		
		executorService.execute(task);
		
		return defResult;
	}
	
	@GetMapping(value="/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public DeferredResult<List<ProductDTO>> searchProducts(@RequestParam(value = "name", required=true) String name) {
		DeferredResult<List<ProductDTO>> deferredResult = new DeferredResult<>();
		
		Runnable task = (() -> {
			List<ProductDTO> products = productCassandraService.searchProductByName(name);
		
			if (products == null || products.size() == 0) {
				deferredResult.setErrorResult(new RestException(HttpStatus.NO_CONTENT, "No Product Found with given Name", ErrorLevel.ERROR));
			}else {
				deferredResult.setResult(products);
			}
		});
		
		executorService.execute(task);
		return deferredResult;
		
	}



}
