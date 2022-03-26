package com.alkemy.ong.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Data
public class SlideRequestDto {

    @NotNull(message = "Image cannot be null")
    private String imgUrl;

    @NotNull(message = "Text cannot be null")
    private String text;

    private Integer order;
    @NotNull(message = "Organization cannot be null")
    private String organizationId;

}
