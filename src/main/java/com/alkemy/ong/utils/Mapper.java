package com.alkemy.ong.utils;

import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.entity.OrganizationEntity;

public class Mapper {
	
	public static OrganizationDto mapToDto(OrganizationEntity organization) {
		OrganizationDto dto = new OrganizationDto();
		dto.setName(organization.getName());
		dto.setImage(organization.getImage());
		dto.setPhone(organization.getPhone());
		dto.setAddress(organization.getAddress());
		return dto;
	}
	
}
