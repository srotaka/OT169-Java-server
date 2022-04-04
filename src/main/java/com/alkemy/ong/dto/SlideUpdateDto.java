package com.alkemy.ong.dto;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SlideUpdateDto {

    private String imgUrl;

    private String text;

    private Integer order;
}
