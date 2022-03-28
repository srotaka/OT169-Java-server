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
	
	@GetMapping("/{id}") //OT169-41
	//@Secured("ROLE_ADMIN")
	public ResponseEntity<Category> getById(@RequestParam (name = "id", required=false) String id) {
		System.out.println("Get by id");
		System.out.println("ID="+id);
		System.out.println(categoryRepository.existsById(id));
		if(categoryRepository.existsById(id)){//If the ID corresponds to an Category, returns it
			return new ResponseEntity<Category>(categoryService.getById(id), HttpStatus.OK);
		}
		//If the ID doesn't corresponds to an Category, sends an error
		return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
				
	}
	
}
