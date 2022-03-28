package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrganizationRequestDto {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    @Email
    private String email;
    @NotNull
    @NotBlank
    private String image;
    @NotNull
    @NotBlank
    private Long phone;
    @NotNull
    @NotBlank
    private String address;
    @NotNull
    @NotBlank
    @Lob
    private String welcomeText;
    @NotNull
    @NotBlank
    @Lob
    private String aboutUsText;
}
