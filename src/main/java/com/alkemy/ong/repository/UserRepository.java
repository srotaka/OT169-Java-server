package com.alkemy.ong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alkemy.ong.entity.User;

@Repository
public interface UserRepository extends JpaRepository <User, String> {

	
	@Query("SELECT a FROM User a WHERE a.email = :email")
	public User getByEmail(@Param("email") String email);

}
