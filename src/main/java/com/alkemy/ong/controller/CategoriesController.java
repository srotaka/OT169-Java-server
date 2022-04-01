package com.alkemy.ong.controller;

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

import com.alkemy.ong.entity.Category;
import com.alkemy.ong.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/")//OT169-40
	public List<String> getNamesFromAll(){
		return categoryService.getNamesFromAll();
	}

	@GetMapping("/{id}") // OT169-41
	public ResponseEntity<Category> getById(@RequestParam(name = "id", required = false) String id) {
		if (categoryService.existsById(id)) {// If the ID corresponds to an Category, returns it
			return new ResponseEntity<Category>(categoryService.getById(id), HttpStatus.OK);
		}
		// If the ID doesn't corresponds to an Category, sends an error
		return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);

	}

	@PostMapping("/") // OT169-42
	public ResponseEntity<Category> createCategory(@RequestBody Category category) { // I get the entity
		return new ResponseEntity<Category>(categoryService.save(category), HttpStatus.OK); // if it doesn't had errors,
	}

	@PutMapping("/{id}") // OT169-43
	public ResponseEntity<Category> updateCategory(@RequestParam(name = "id") String id, // I get the ID
			@RequestBody Category category) { // I get the Category to be updated
		if (categoryService.existsById(id)) {// If the category exists
			return new ResponseEntity<Category>(categoryService.save(category), HttpStatus.OK); // I update it.
		}
		// If it doesn't exists, then I return a 500 error code
		return new ResponseEntity<Category>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@DeleteMapping("/{id}") // OT169-44
	public ResponseEntity<Category> deleteCategory(@RequestParam(name = "id") String id, // I get the ID
			@RequestBody Category category) { // I get the Category to be deleted
		if (categoryService.existsById(id)) {// If the category exists
			categoryService.delete(category);// I delete it
			return new ResponseEntity<Category>(HttpStatus.OK); // I return a 200 code
		}
		// If it doesn't exists, then I return a 500 error code
		return new ResponseEntity<Category>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}