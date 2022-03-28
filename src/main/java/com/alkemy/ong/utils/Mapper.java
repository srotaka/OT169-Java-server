package com.alkemy.ong.utils;

import com.alkemy.ong.dto.ActivityDto;
import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.entity.Activity;
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

	public static ActivityDto mapToDto(Activity activity) {
		ActivityDto dto = new ActivityDto();
		dto.setId(activity.getId());
		dto.setName(activity.getName());
		dto.setContent(activity.getContent());
		dto.setImage(activity.getImage());
		return dto;
	}

	public static Activity mapFromDto(ActivityDto dto) {
		Activity activity = new Activity();
		activity.setId(dto.getId());
		activity.setName(dto.getName());
		activity.setContent(dto.getContent());
		activity.setImage(dto.getImage());
		return activity;
	}
	
}
