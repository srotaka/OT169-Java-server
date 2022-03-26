package com.alkemy.ong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
	 * Method only allowed to admins. The method returns the created entitty. If it doesn't work, then returns a 500 error code.
	 */
	@PostMapping("/")//OT169-42
	@Secured("ROLE_ADMIN")
	public ResponseEntity<Category> createCategory(@RequestBody Category category){ // I get the entity
		return new ResponseEntity<Category>(categoryRepository.save(category), HttpStatus.OK); //if it doesn't had errors, return it with a 200 code.
		
	}
	
	
	
}