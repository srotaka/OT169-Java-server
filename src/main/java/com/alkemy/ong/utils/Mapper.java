package com.alkemy.ong.utils;

import com.alkemy.ong.dto.*;
import com.alkemy.ong.entity.*;

public class Mapper {

	public static OrganizationResponseDto mapToDto(Organization organization, OrganizationResponseDto dto) {
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

	public static Organization mapFromDto(OrganizationRequestDto dto, Organization organization) {
		organization.setName(dto.getName());
		organization.setImage(dto.getImage());
		organization.setEmail(dto.getEmail());
		organization.setPhone(dto.getPhone());
		organization.setAddress(dto.getAddress());
		organization.setWelcomeText(dto.getWelcomeText());
		organization.setAboutUsText(dto.getAboutUsText());
		return organization;
		
    }

	public static ActivityDto mapToDto(Activity activity, ActivityDto dto) {
		dto.setId(activity.getId());
		dto.setName(activity.getName());
		dto.setContent(activity.getContent());
		dto.setImage(activity.getImage());
		return dto;
	}

	public static Activity mapFromDto(ActivityDto dto, Activity activity) {
		activity.setName(dto.getName());
		activity.setContent(dto.getContent());
		activity.setImage(dto.getImage());
		return activity;
	}

	public static UserDto mapToUserDto(User user) {

		return new UserDto(
				user.getFirstName(),
				user.getLastName(),
				user.getEmail(),
				user.getPhoto());
	}

	public static SlideDto mapToDto(Slide slide, SlideDto slideDto){
		slideDto.setImageUrl(slide.getImageUrl());
		slideDto.setOrder(slide.getOrder());
		return slideDto;
	}
}
