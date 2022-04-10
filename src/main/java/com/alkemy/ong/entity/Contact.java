package com.alkemy.ong.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;


@Entity
@Setter
@Getter
@Table(name = "contacts")
@SQLDelete(sql = "UPDATE contacts SET soft_delete = true WHERE id=?")
@Where(clause = "soft_delete = false")
@ApiModel(description = "Details about the Contact")
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @ApiModelProperty(notes = "The unique id of the Contact")
    private String id;

    @ApiModelProperty(notes = "The Contact's name",position = 1)
    private String name;

    @ApiModelProperty(notes = "The Contact's phone",position = 2)
    private String phone;

    @ApiModelProperty(notes = "The Contact's email",position = 3)
    private String email;

    @ApiModelProperty(notes = "The Contact's message",position = 4)
    private String message;

    @ApiModelProperty(notes = "The Contact's timestamp creation",position = 5)
    private Timestamp timestamp = Timestamp.from(Instant.now());

    @ApiModelProperty(notes = "The Contact's logic delete flag",position = 6)
    private boolean softDelete = Boolean.FALSE;

}
