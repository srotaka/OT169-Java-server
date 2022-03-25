package com.alkemy.ong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
	 * @author Lamberti
	 * Method only allowed to admins. If the ID is of an existing entity, the method returns it. If it doesn't exists, then returns a 404 error code.
	 */
	@GetMapping("/{id}") //OT169-41
	@Secured("ROLE_ADMIN")
	public ResponseEntity<Category> getById(@RequestParam (name = "id") String id) {
		if(categoryRepository.existsById(id)){//If the ID corresponds to an Category, returns it
			return new ResponseEntity<Category>(categoryRepository.getById(id), HttpStatus.OK);
		}
		//If the ID doesn't corresponds to an Category, sends an error
		return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
				
	}
	
}