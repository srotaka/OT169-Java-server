package com.alkemy.ong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.entity.Category;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")//OT169-42
	@PreAuthorize("hasRole('ROLE_ADMIN')") // This method only permits the current role to enter this endpoint
	public ResponseEntity<Category> createCategory(@RequestBody Category category){ // I get the entity
		System.out.println("Create");
		return new ResponseEntity<Category>(categoryService.save(category), HttpStatus.OK); //if it doesn't had errors, return it with a 200 code.
		
	}
	
	
}
