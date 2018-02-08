package com.nisum.fcc.test.service;

import com.nisum.fcc.cassandra.model.Category;
import com.nisum.fcc.cassandra.repository.CategoryRepository;
import com.nisum.fcc.cassandra.service.impl.CategoryServiceImpl;
import com.nisum.fcc.dto.CategoryDTO;
import com.nisum.fcc.service.CategoryService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@ContextConfiguration
public class CategoryServiceTest  extends BaseServiceTest {
	
	@Autowired
	private CategoryService categoryService;
	@MockBean
	private CategoryRepository categoryRepository;

	@Configuration
	static class ContextConfiguration {
		@Bean
		CategoryService categoryService() { return new CategoryServiceImpl(); }
	}

	@Test
	public void getCategoriesTest() throws Exception {
		List<String> subCategories = new ArrayList<String>();
		subCategories.add("boots");
		subCategories.add("kitchen");
		subCategories.add("luggage & backpacks");
		CategoryDTO categoryDto = new CategoryDTO("home",subCategories);
		List<CategoryDTO> categoryDtoList = new ArrayList<CategoryDTO>();
		categoryDtoList.add(categoryDto);
	//	Mockito.when(categoryRepository.getAllCategories("root")).thenReturn();
		assertEquals(categoryDtoList, categoryService.getCategories());
	}
	
}
