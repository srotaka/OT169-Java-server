package com.alkemy.ong.controller;

import com.alkemy.ong.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.entity.Category;
import com.alkemy.ong.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
	
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@DeleteMapping("/{id}")//OT169-44
	@PreAuthorize("hasRole('ROLE_ADMIN')") // This method only permits the current role to enter this endpoint
	public ResponseEntity<Category> deleteCategory(@RequestParam (name = "id") String id, // I get the ID
			@RequestBody Category category) { //I get the Category to be deleted
		if (categoryRepository.existsById(id)) {//If the category exists
			categoryService.delete(category);// I delete it
			return new ResponseEntity<Category>(HttpStatus.OK); //I return a 200 code 
		}
		return ResponseEntity.notFound().build();
	}

}
