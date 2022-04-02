package com.alkemy.ong.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
public class ContactDto implements Serializable {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String name;

    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String phone;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    @NotNull
    @NotEmpty
    private String message;

}
