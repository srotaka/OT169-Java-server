package com.alkemy.ong.utils;

import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.entities.OrganizationMock;

public class Mapper {
	
	public static OrganizationDto mapToDto(OrganizationMock organizationMock) {
		OrganizationDto dto = new OrganizationDto();
		dto.setName(organizationMock.getName());
		dto.setImage(organizationMock.getImage());
		dto.setPhone(organizationMock.getPhone());
		dto.setAddress(organizationMock.getAddress());
		return dto;
	}
	
}
