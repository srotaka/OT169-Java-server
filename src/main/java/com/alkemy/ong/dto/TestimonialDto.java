package com.alkemy.ong.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Details about Testimonial's DTO")
public class TestimonialDto {

    @ApiModelProperty(notes = "The unique id of the Testimonial")
    private String id;

    @NotNull
    @NotBlank
    @ApiModelProperty(notes = "The Testimonial's DTO name",required = true,position = 1)
    private String name;

    @NotNull
    @NotBlank
    @ApiModelProperty(notes = "The Testimonial's DTO image",required = true,position = 2)
    private String image;

    @NotNull
    @NotBlank
    @ApiModelProperty(notes = "The Testimonial's DTO content",required = true,position = 3)
    private String content;

}
