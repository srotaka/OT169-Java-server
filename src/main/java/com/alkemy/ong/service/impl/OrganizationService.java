package com.alkemy.ong.service.impl;

import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.entities.OrganizationMock;
import com.alkemy.ong.service.IOrganizationService;
import com.alkemy.ong.utils.Mapper;

@Service
public class OrganizationService implements IOrganizationService {

	@Override
	public OrganizationDto getPublicInfo() {
		OrganizationMock organization = new OrganizationMock();
		organization.setName("Organization Name");
		organization.setImage("Organization.jpg");
		organization.setPhone(Integer.valueOf(123456789));
		organization.setAddress("Organization address");
		organization.setSecretValue(1);
		
		return Mapper.mapToDto(organization);
	}
	
}
