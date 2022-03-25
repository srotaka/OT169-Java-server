package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.OrganizationRequestDto;
import com.alkemy.ong.entity.OrganizationEntity;
import com.alkemy.ong.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.OrganizationResponseDto;
import com.alkemy.ong.service.IOrganizationService;
import com.alkemy.ong.utils.Mapper;

@Service
public class OrganizationService implements IOrganizationService {

	@Autowired
	private OrganizationRepository repository;

	@Override
	public OrganizationResponseDto getPublicInfo() {
		return Mapper.mapToDto(repository.findAll().get(0), new OrganizationResponseDto());
	}

	@Override
	public OrganizationResponseDto postPublicInfo(OrganizationRequestDto organizationRequestDto) {

		if(repository.count() == 0) {
			OrganizationEntity organization = new OrganizationEntity();
			organization.setName("Organization Name");
			organization.setEmail("organization@mail.com");
			organization.setImage("organizationImage.jpg");
			organization.setWelcomeText("Welcome to the organization");
			repository.save(organization);
		}
		OrganizationEntity updatedOrganization = Mapper.mapFromDto(organizationRequestDto,
				                                                   repository.findAll().get(0));
		repository.save(updatedOrganization);
		return Mapper.mapToDto(updatedOrganization, new OrganizationResponseDto());
	}

}
