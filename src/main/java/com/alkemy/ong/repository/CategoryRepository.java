package com.alkemy.ong.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alkemy.ong.dto.CategoryDTO;
import com.alkemy.ong.entity.Category;

import com.alkemy.ong.dto.CategoryDTO;
import com.alkemy.ong.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> { //Updated by Franco Lamberti (UUID --> String)
		
	//Added by Franco Lamberti
	@Query("SELECT a.name FROM Category a")
	public List<Category> getNamesFromAll();
	
	@Query("SELECT a FROM Category a WHERE id = :id")
	public List<CategoryDTO> getDTOById(@Param("id")String id);
	

}
