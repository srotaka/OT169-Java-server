package com.alkemy.ong.dto;

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
public class NewsDto {

    private String id;

    @NotNull
    @NotBlank(message = "You must provide a name")
    private String name;

    @NotNull
    @NotBlank(message = "you must provide a content")
    private String content;

    private String image;

    private List<CategoryBasicDto> categories;

}
