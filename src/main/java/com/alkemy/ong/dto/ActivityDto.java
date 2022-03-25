package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ActivityDto {

    private String id;
    @NotNull
    @NotBlank(message = "You must provide a name.")
    private String name;
    @NotNull
    @NotBlank(message = "You must provide a content.")
    @Lob
    private String content;
    @NotNull
    @NotBlank(message = "You must provide an image")
    private String image;
}
