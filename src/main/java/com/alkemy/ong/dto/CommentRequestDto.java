package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequestDto {

    @NotBlank(message = "You must provide a body")
    @NotNull
    private String body;

    @NotBlank(message = "You must provide a post_id")
    @NotNull
    private String post_id;

    @NotBlank(message = "You must provide a user_id")
    @NotNull
    private String user_id;
}
