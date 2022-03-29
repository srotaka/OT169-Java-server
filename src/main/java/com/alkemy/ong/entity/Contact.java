package com.alkemy.ong.entity;

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
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    private String phone;
    private String email;
    private String message;

    private Timestamp timestamp = Timestamp.from(Instant.now());
    private boolean softDelete = Boolean.FALSE;

}
