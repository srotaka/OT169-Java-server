package com.alkemy.ong.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "Details about the Activity's DTO")
public class ActivityDto {

    @ApiModelProperty(notes = "The unique id of the Activity's DTO")
    private String id;
    @ApiModelProperty(notes = "The Activity's DTO name",required = true,position = 1)
    @NotNull
    @NotBlank(message = "You must provide a name.")
    private String name;
    @ApiModelProperty(notes = "The Activity's DTO content",required = true,position = 2)
    @NotNull
    @NotBlank(message = "You must provide a content.")
    @Lob
    private String content;
    @ApiModelProperty(notes = "The Activity's DTO image",position = 3)
    private String image;
}
