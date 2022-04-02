package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class SlideDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String imageUrl;
    private Integer order;

}