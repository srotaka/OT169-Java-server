package com.alkemy.ong.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name="role")
@SQLDelete(sql = "UPDATE role SET softDeleted = true WHERE id=?")
@Where(clause="deleted=false")
public class Role {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamps;

    private boolean softDeleted = Boolean.FALSE;


}
