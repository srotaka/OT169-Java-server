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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.dto.CategoryDTO;

import com.alkemy.ong.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")//OT169-42
	@PreAuthorize("hasRole('ROLE_ADMIN')") // This method only permits the current role to enter this endpoint
	public ResponseEntity<Category> createCategory(@RequestBody Category category){ // I get the entity
		System.out.println("Create");
		return new ResponseEntity<Category>(categoryService.save(category), HttpStatus.OK); //if it doesn't had errors, return it with a 200 code.
		
	}
	
	/**
	 * @author Franco Lamberti
	 * This method is only allowed to Admins. Returns all the names of the categories.
	 */
	@GetMapping("/")// OT169-40
	@PreAuthorize("hasRole('ROLE_ADMIN')") // This method only permits the current role to enter this endpoint
	public List<CategoryDTO> getNamesFromAll(){
		System.out.println("Get all");
		return categoryService.getAllCategories();
	}	
}
