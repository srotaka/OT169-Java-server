package com.alkemy.ong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
	 * This method is only allowed to Admins. Returns all the categories.
	 */
	@GetMapping("/")// OT169-40
	@Secured("ROLE_ADMIN") // This method only permits the current role to enter this endpoint
	public List<Category> getAll(){
		return categoryRepository.findAll();
	}
	
	
	
}
