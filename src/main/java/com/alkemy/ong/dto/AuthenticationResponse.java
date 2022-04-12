package com.alkemy.ong.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@ApiModel(description = "Details about jwt for authentication")
public class AuthenticationResponse {

    @ApiModelProperty(notes = "The Jwt token value for future request")
    private final String jwt;
}
