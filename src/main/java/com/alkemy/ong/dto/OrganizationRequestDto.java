package com.alkemy.ong.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "Details about Organization's DTO")
public class OrganizationRequestDto {
    @NotNull
    @NotBlank
    @ApiModelProperty(notes = "The Organization's DTO name",required = true)
    private String name;
    @NotNull
    @NotBlank
    @Email
    @ApiModelProperty(notes = "The Organization's DTO email",required = true,position = 1)
    private String email;
    @NotNull
    @NotBlank
    @ApiModelProperty(notes = "The Organization's DTO facebook url",required = true,position = 2)
    private String facebookUrl;
    @NotNull
    @NotBlank
    @ApiModelProperty(notes = "The Organization's DTO linkedin url",required = true,position = 3)
    private String linkedinUrl;
    @NotNull
    @NotBlank
    @ApiModelProperty(notes = "The Organization's DTO instagram url",required = true,position = 4)
    private String instagramUrl;
    @NotNull
    @NotBlank
    @ApiModelProperty(notes = "The Organization's DTO image",required = true,position = 5)
    private String image;
    @NotNull
    @ApiModelProperty(notes = "The Organization's DTO phone",required = true,position = 6)
    private Long phone;
    @NotNull
    @NotBlank
    @ApiModelProperty(notes = "The Organization's DTO address",required = true,position = 7)
    private String address;
    @NotNull
    @NotBlank
    @Lob
    @ApiModelProperty(notes = "The Organization's DTO welcome text",required = true,position = 8)
    private String welcomeText;
    @NotNull
    @NotBlank
    @Lob
    @ApiModelProperty(notes = "The Organization's DTO about us text",required = true,position = 9)
    private String aboutUsText;
}
