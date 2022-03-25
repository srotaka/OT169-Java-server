package com.alkemy.ong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alkemy.ong.entity.Category;
import com.alkemy.ong.repository.CategoryRepository;

@Controller
@RequestMapping("/categories")
/**
 * @author Franco Lamberti
 * 
 */
public class CategoriesController {
	
	@Autowired
	private CategoryRepository categoryRepository;	
	/**
	 * @author Franco Lamberti
	 * Method only allowed to admins. If the ID is of an existing entity, the method returns it updated version. If it doesn't exists, then returns a 500 error code.
	 */
	@PutMapping("/{id}")//OT169-43
	@Secured("ROLE_ADMIN")
	public ResponseEntity<Category> updateCategory(@RequestParam (name = "id") String id, // I get the ID
			@RequestBody Category category){ //I get the Category to be updated
		if(categoryRepository.existsById(id)) {//If the category exists			
			return new ResponseEntity<Category>(categoryRepository.save(category), HttpStatus.OK); //I update it. 
		}
		//If it doesn't exists, then I return a 500 error code
		return new ResponseEntity<Category>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
}