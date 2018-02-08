package com.nisum.fcc.cassandra.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.fcc.cassandra.model.Category;
import com.nisum.fcc.cassandra.repository.CategoryRepository;
import com.nisum.fcc.dto.CategoryDTO;
import com.nisum.fcc.service.CategoryService;

@Service("categoryCassandraService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryCassandraRepository;

	@Override
	public List<CategoryDTO> getCategories() {
		List<Category> categories =  categoryCassandraRepository.getAllCategories("root");
		return categories.iterator().next().getValue().stream().map(category -> 
		new CategoryDTO(category, getSubCategories(category))			
		).collect(Collectors.toList()) ;
		
		
	}

	/**
	 * 
	 * @param category
	 * @return
	 */
	
	private List<String> getSubCategories(String category) {
		List<Category> subCategories = categoryCassandraRepository.getAllCategories(category);
		return !subCategories.isEmpty() ? new ArrayList<String>(subCategories.iterator().next().getValue()) : null ;  
	}

}
