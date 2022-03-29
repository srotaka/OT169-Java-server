package com.alkemy.ong.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor// Added by Franco Lamberti
@NoArgsConstructor// Added by Franco Lamberti
@Table(name="role")
@SQLDelete(sql = "UPDATE role SET soft_deleted = true WHERE id=?")
@Where(clause="soft_deleted = false")

public class Role {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "role_id")
    private String id;

    private String name;

    private String description;

    private Timestamp timestamp = Timestamp.from(Instant.now());

    private boolean soft_deleted = Boolean.FALSE;

    public Role(String id) {
    	this.id = id;
    }

}