package com.alkemy.ong.service;

import com.alkemy.ong.dto.OrganizationRequestDto;
import com.alkemy.ong.dto.OrganizationResponseDto;

public interface IOrganizationService {
	
	OrganizationResponseDto getPublicInfo();

	OrganizationResponseDto postPublicInfo(OrganizationRequestDto organizationRequestDto);
}
