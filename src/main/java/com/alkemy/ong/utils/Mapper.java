package com.alkemy.ong.utils;

import com.alkemy.ong.dto.ActivityDto;
import com.alkemy.ong.dto.OrganizationRequestDto;
import com.alkemy.ong.dto.OrganizationResponseDto;
import com.alkemy.ong.entity.Activity;
import com.alkemy.ong.entity.OrganizationEntity;

public class Mapper {
	
	public static OrganizationResponseDto mapToDto(OrganizationEntity organization, OrganizationResponseDto dto) {
		dto.setName(organization.getName());
		dto.setImage(organization.getImage());
		dto.setPhone(organization.getPhone());
		dto.setAddress(organization.getAddress());
		return dto;
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

}
