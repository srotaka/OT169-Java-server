package com.alkemy.ong.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SlideResponseDto {
    private String imgUrl;

    private Integer order;

    private String text;

    private OrganizationResponseDto org;
}
