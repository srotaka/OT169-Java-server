package com.alkemy.ong.service.impl;

import com.alkemy.ong.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.entities.OrganizationMock;
import com.alkemy.ong.service.IOrganizationService;
import com.alkemy.ong.utils.Mapper;

@Service
public class OrganizationService implements IOrganizationService {

	@Autowired
	private OrganizationRepository repository;

	@Override
	public OrganizationDto getPublicInfo() {
		return Mapper.mapToDto(repository.findAll().get(0));
	}
	
}
