package com.alkemy.ong.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.ong.model.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, String>{
	Optional<Activity> findByName(String name);
}
