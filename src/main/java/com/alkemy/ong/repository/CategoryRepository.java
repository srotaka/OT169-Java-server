package com.alkemy.ong.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alkemy.ong.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> { //Updated by Franco Lamberti (UUID --> String)
		
	//Added by Franco Lamberti
	@Query("SELECT a.name FROM Category a")
	public List<Category> getNamesFromAll();
	
}
