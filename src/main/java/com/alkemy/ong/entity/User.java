package com.alkemy.ong.entity;

import java.sql.Timestamp;
import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.ForeignKey;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE users SET soft_delete = TRUE WHERE id=?")
@Where(clause = "soft_delete = false")
@ApiModel(description = "Details about the user")
public class User {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    @ApiModelProperty(notes = "The unique id of the user")
	private String id;
	
    @NotNull
    @NotBlank
    @Column(nullable = false)
    @ApiModelProperty(notes = "The User's firstname",required = true,position = 1)
    private String firstName;

    @Column(nullable = false)
    @NotNull
    @NotBlank
    @ApiModelProperty(notes = "The User's lastname",required = true,position = 2)
    private String lastName;

    @Column(nullable = false,unique = true)
    @NotNull
    @NotBlank
    @Email
    @ApiModelProperty(notes = "The User's email",required = true,position = 3)
    private String email;

    @Column(nullable = false)
    @NotNull
    @NotBlank
    @ApiModelProperty(notes = "The User's password",required = true,position = 4)
    private String password;

    @ApiModelProperty(notes = "The User's photo",position = 5)
    @Nullable
    private String photo;

    @ApiModelProperty(notes = "The User's timestamp creation",position = 6)
    private Timestamp timestamp = Timestamp.from(Instant.now());

    @ManyToOne(fetch = FetchType.EAGER ,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "role_id",nullable = false,foreignKey=@ForeignKey(name = "FK_role"))
    @ApiModelProperty(notes = "The User's id role",required = true,position = 7)
    private Role roleId;
    

    @Column(name = "soft_delete")
    @ApiModelProperty(notes = "The User's logic delete flag",position = 8)
    private boolean soft_delete = Boolean.FALSE;


}
