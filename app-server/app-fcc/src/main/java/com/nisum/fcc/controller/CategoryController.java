package com.nisum.fcc.controller;

import com.nisum.common.constant.ErrorLevel;
import com.nisum.common.exception.rest.RestException;
import com.nisum.fcc.dto.CategoryDTO;
import com.nisum.fcc.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * The Class CategoryController.
 */
@RestController
@RequestMapping("categories")
public class CategoryController extends BaseController {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

	/** The category service. */
	@Autowired
	private CategoryService categoryCassandraService;

	@Autowired
	private ExecutorService executorServie;
	/**
	 * List.
	 *
	 * @return the list
	 */
	@GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public DeferredResult<List<CategoryDTO>> list() {
		DeferredResult<List<CategoryDTO>> deferredResult =  new DeferredResult<>();
		
		
		Runnable task = () -> {
			List<CategoryDTO> categoryDtoList = categoryCassandraService.getCategories();
			
			if (categoryDtoList == null || categoryDtoList.size() == 0) {
				deferredResult.setErrorResult(new RestException(HttpStatus.NO_CONTENT, "Categories List Is Empty", ErrorLevel.ERROR));
			}else {
				deferredResult.setResult(categoryDtoList);
			}
		};
		
		executorServie.execute(task);
		
		return deferredResult;
	}

}