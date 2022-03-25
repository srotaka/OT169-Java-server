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
	 * @author Franco Lamberti
	 * The id is sent by parameters, if any Category has that ID then, the method returns the entity. If it doesn't, returns a 404 Error code.
	 */
	@GetMapping("/{id}") //OT169-41
	@Secured("ROLE_ADMIN")
	public ResponseEntity<Category> getById(@RequestParam (name = "id") String id) {
		if(categoryRepository.existsById(id)){//If the ID corresponds to an Category, return it
			return new ResponseEntity<Category>(categoryRepository.getById(id), HttpStatus.OK);
		}
		//If the ID doesn't corresponds to an Category, send an error
		return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
				
	}
	
	
	
}
