package com.alkemy.ong.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Details about Organization's Response DTO")
public class OrganizationResponseDto {
	@ApiModelProperty(notes = "The Organization's Response DTO name")
	private String name;

	@ApiModelProperty(notes = "The Organization's Response DTO image",position = 1)
	private String image;

	@ApiModelProperty(notes = "The Organization's Response DTO phone",position = 2)
	private Long phone;

	@ApiModelProperty(notes = "The Organization's Response DTO address",position = 3)
	private String address;

	@ApiModelProperty(notes = "The Organization's Response DTO contacts",position = 4)
	private Map<String, String> contact;
}
