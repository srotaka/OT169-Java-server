package com.alkemy.ong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.dto.CategoryDTO;
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

	/**
	 * @author Franco Lamberti
	 * This method is only allowed to Admins. Returns all the categories.
	 */
	@GetMapping("/")// OT169-40
	//@Secured("ROLE_ADMIN") // This method only permits the current role to enter this endpoint
	public List<CategoryDTO> getNamesFromAll(){
		System.out.println("Get all");
		return categoryService.getAllCategories();
	}	
}
