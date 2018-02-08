/**
 * 
 */
package com.nisum.fcc.cassandra.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nisum.fcc.cassandra.model.Category;

/**
 * @author Nisum Pakistan
 *
 */

public interface CategoryRepository extends CrudRepository<Category, String>{
	
	@Query(value="SELECT * FROM app_list where key=?0")
	public List<Category> getAllCategories(String category);
}
