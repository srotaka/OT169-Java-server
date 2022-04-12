package com.alkemy.ong.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Details about New's DTO")
public class NewsDto {

    @ApiModelProperty(notes = "The unique id of the New's DTO")
    private String id;

    @NotNull
    @NotBlank(message = "You must provide a name")
    @ApiModelProperty(notes = "The New's DTO name",required = true,position = 1)
    private String name;

    @NotNull
    @NotBlank(message = "you must provide a content")
    @ApiModelProperty(notes = "The New's DTO content",required = true,position = 2)
    private String content;

    @NotNull
    @NotBlank
    @ApiModelProperty(notes = "The New's DTO image",required = true,position = 3)
    private String image;

    @ApiModelProperty(notes = "The New's DTO list of categories",required = true,position = 4)
    private List<CategoryBasicDto> categories;

}
