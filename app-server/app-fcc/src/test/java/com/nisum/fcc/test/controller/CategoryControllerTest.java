package com.nisum.fcc.test.controller;

import com.nisum.common.controller.advice.ExceptionHandlerAdvice;
import com.nisum.fcc.controller.CategoryController;
import com.nisum.fcc.dto.CategoryDTO;
import com.nisum.fcc.service.CategoryService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = CategoryController.class, secure = false)
public class CategoryControllerTest extends BaseControllerTest {
	@MockBean
	private CategoryService categoryService;

	@Autowired
	private ExecutorService executorServie;

	private final String CATEGORY_CONTROLLER_URL_GET_CATEGORY_LIST ="/categories";
	
	@Test
	public void listTest() throws Exception {
		List<String> subCategories = new ArrayList<String>();
		subCategories.add("boots");
		subCategories.add("kitchen");
		subCategories.add("luggage & backpacks");
		CategoryDTO categoryDto = new CategoryDTO("home",subCategories);
		List<CategoryDTO> categoryDtoList = new ArrayList<CategoryDTO>();
		categoryDtoList.add(categoryDto);
		Mockito.when(categoryService.getCategories()).thenReturn(categoryDtoList);
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(CATEGORY_CONTROLLER_URL_GET_CATEGORY_LIST)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		mvc.perform(asyncDispatch(result))
				.andExpect(status().isOk())
				.andExpect(jsonPath("[0].id").value("home"))
				.andExpect(jsonPath("[0].name").value("home"))
				.andExpect(jsonPath("[0].subCategories[0].id").value("home-boots"))
				.andExpect(jsonPath("[0].subCategories[0].name").value("boots"))
				.andExpect(jsonPath("[0].subCategories[1].id").value("home-kitchen"))
				.andExpect(jsonPath("[0].subCategories[1].name").value("kitchen"))
				.andExpect(jsonPath("[0].subCategories[2].id").value("home-luggage & backpacks"))
				.andExpect(jsonPath("[0].subCategories[2].name").value("luggage & backpacks"));
	}
	
	@Test(expected = Exception.class)
	public void emptyListTest() throws Exception {
		Mockito.when(categoryService.getCategories()).thenReturn(null);
		mvc = MockMvcBuilders.standaloneSetup(new CategoryController())
				.setControllerAdvice(new ExceptionHandlerAdvice()).build();
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(CATEGORY_CONTROLLER_URL_GET_CATEGORY_LIST)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		mvc.perform(asyncDispatch(result))
				.andExpect(status().isNoContent());
	}
	
	
}
