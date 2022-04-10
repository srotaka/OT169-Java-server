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
@ApiModel(description = "Details about the Comment DTO")
public class CommentRequestDto {

    @NotBlank(message = "You must provide a body")
    @NotNull
    @ApiModelProperty(notes = "The Comment's DTO body",required = true)
    private String body;

    @NotBlank(message = "You must provide a post_id")
    @NotNull
    @ApiModelProperty(notes = "The Comment's DTO post id ",required = true,position = 1)
    private String post_id;

    @NotBlank(message = "You must provide a user_id")
    @NotNull
    @ApiModelProperty(notes = "The Comment's DTO user id",required = true,position = 2)
    private String user_id;
}
