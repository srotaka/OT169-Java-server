package com.alkemy.ong.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "Details about Comment Reponse DTO")
public class CommentResponseDto {

    @Lob
    @ApiModelProperty(notes = "The Comment's Reponse DTO body")
    private String body;

}
