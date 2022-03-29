package com.alkemy.ong.controller;

import com.alkemy.ong.dto.OrganizationRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.alkemy.ong.dto.OrganizationResponseDto;
import com.alkemy.ong.service.impl.OrganizationService;

import javax.validation.Valid;


@RestController
@RequestMapping("/organization")
@Validated
@CrossOrigin
public class OrganizationController {
	
	@Autowired
	private OrganizationService service;

	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping("/public") 
	public OrganizationResponseDto getPublicInfo() {
		return service.getPublicInfo();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/public")
	public OrganizationResponseDto postPublicInfo(@RequestBody @Valid OrganizationRequestDto organizationRequestDto) {
		return service.postPublicInfo(organizationRequestDto);
	}

}
