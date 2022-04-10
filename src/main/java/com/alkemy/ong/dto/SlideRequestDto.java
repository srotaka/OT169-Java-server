package com.alkemy.ong.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Details about Slide's DTO")
public class SlideRequestDto {

    @NotNull(message = "Image cannot be null")
    @NotBlank
    @ApiModelProperty(notes = "The Slide's DTO image url",required = true)
    private String imgUrl;

    @NotNull(message = "Text cannot be null")
    @NotBlank
    @ApiModelProperty(notes = "The Slide's DTO text",required = true,position = 1)
    private String text;

    @ApiModelProperty(notes = "The Slide's DTO order",position = 2)
    private Integer order;

    @NotNull(message = "Organization cannot be null")
    @NotBlank
    @ApiModelProperty(notes = "The Slide's DTO organization id",required = true,position = 3)
    private String organizationId;

}
