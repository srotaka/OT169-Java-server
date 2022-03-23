package com.alkemy.ong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.service.impl.OrganizationService;


@RestController
@RequestMapping("/organization")
public class OrganizationController {
	
	@Autowired
	private OrganizationService service;
	
	@GetMapping("/public") 
	public OrganizationDto getPublicInfo() {
		return service.getPublicInfo();
	}

}
