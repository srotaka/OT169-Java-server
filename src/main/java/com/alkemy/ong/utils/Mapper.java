package com.alkemy.ong.utils;

import com.alkemy.ong.dto.CategoryBasicDto;
import com.alkemy.ong.entity.Category;
import com.alkemy.ong.dto.OrganizationRequestDto;
import com.alkemy.ong.dto.OrganizationResponseDto;
import com.alkemy.ong.entity.OrganizationEntity;

public class Mapper {
	
	public static OrganizationResponseDto mapToDto(OrganizationEntity organization, OrganizationResponseDto dto) {
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

    public static OrganizationEntity mapFromDto(OrganizationRequestDto dto,
												OrganizationEntity organization) {
		organization.setName(dto.getName());
		organization.setImage(dto.getImage());
		organization.setEmail(dto.getEmail());
		organization.setPhone(dto.getPhone());
		organization.setAddress(dto.getAddress());
		organization.setWelcomeText(dto.getWelcomeText());
		organization.setAboutUsText(dto.getAboutUsText());
		return organization;
		
    }

}
