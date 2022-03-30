package com.alkemy.ong.dto;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SlideRequestDto {

    @NotNull(message = "Image cannot be null")
    @NotBlank
    private String imgUrl;

    @NotNull(message = "Text cannot be null")
    @NotBlank
    private String text;

    private Integer order;
    @NotNull(message = "Organization cannot be null")
    @NotBlank
    private String organizationId;

}
