package com.alkemy.ong.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;


@Entity
@Table(name = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE categories SET soft_delete = true WHERE id=?")
@Where(clause = "soft_delete=false")
@ApiModel(description = "Details about the Category")
public class Category {


    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @ApiModelProperty(notes = "The unique of the Category")
    private String id;

    @NonNull
    @ApiModelProperty(notes = "The Category's name",required = true,position = 1)
    private String name;
    @Nullable
    @ApiModelProperty(notes = "The Category's description",position = 2)
    private String description;
    @Nullable
    @ApiModelProperty(notes = "The Category's image",position = 3)
    private String image;
    @ApiModelProperty(notes = "The Category's timestamp creation",position = 4)
    private Timestamp timestamp = Timestamp.from(Instant.now());
    @Column(name = "soft_delete")
    @ApiModelProperty(notes = "The Category's logic delete flag",position = 5)
    private boolean soft_delete = Boolean.FALSE;

}
