package com.alkemy.ong.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@SQLDelete(sql = "UPDATE role SET softDeleted = true WHERE id=?")
@Where(clause="deleted=false")
public class Role {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    private String description;

    //@Temporal(TemporalType.TIMESTAMP) //The code doesn't compile with this annotation. Updated by Franco Lamberti
    private Date timestamps;

    private boolean softDeleted = Boolean.FALSE;

    //Added by Franco Lamberti
    public Role(String id) {
    	this.id = id;
    }

}