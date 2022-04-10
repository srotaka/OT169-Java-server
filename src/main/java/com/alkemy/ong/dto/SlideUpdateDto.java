package com.alkemy.ong.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ApiModel(description = "Details about Slide's update DTO")
public class SlideUpdateDto {

    @ApiModelProperty(notes = "The Slide's update DTO image url")
    private String imgUrl;

    @ApiModelProperty(notes = "The Slide's update DTO text",position = 1)
    private String text;

    @ApiModelProperty(notes = "The Slide's update DTO order",position = 2)
    private Integer order;
}
