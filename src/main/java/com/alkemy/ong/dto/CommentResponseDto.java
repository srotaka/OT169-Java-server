package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentResponseDto {

    @Lob
    private String body;

}
