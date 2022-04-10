package com.alkemy.ong.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "Details about the credentials needed to login in the api")
public class UserCredentialsDto {

    @NotNull
    @NotBlank
    @ApiModelProperty(notes = "The registered user's email",required = true)
    private String email;
    @NotNull
    @NotBlank
    @ApiModelProperty(notes = "The registered user's password",required = true,position = 1)
    private String password;


}
