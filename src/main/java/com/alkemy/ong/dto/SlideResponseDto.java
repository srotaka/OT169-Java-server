package com.alkemy.ong.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Details about Slide's Response DTO")
public class SlideResponseDto {
    @ApiModelProperty(notes = "The Slide's Response DTO image url")
    private String imgUrl;

    @ApiModelProperty(notes = "The Slide's Response DTO order",position = 1)
    private Integer order;

    @ApiModelProperty(notes = "The Slide's Response DTO text",position = 2)
    private String text;

    @ApiModelProperty(notes = "The Slide's Response DTO organization",position = 3)
    private String org;
}
