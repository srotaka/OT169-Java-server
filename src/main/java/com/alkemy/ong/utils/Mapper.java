package com.alkemy.ong.utils;

import com.alkemy.ong.dto.CategoryBasicDto;
import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.entity.Category;
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

	public static Category mapToEntity(CategoryBasicDto categoryDto, Category category) {
			category.setName(categoryDto.getName());
		return category;
	}

	public static CategoryBasicDto mapToDto(Category category, CategoryBasicDto basicDto) {
		basicDto.setName(category.getName());

		return basicDto;
	}




	
}
