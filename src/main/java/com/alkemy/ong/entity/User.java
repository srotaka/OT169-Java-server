package com.alkemy.ong.entity;

<<<<<<< HEAD
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
=======
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

>>>>>>> 3f4d31c (Unidirectional User->Role)
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

<<<<<<< HEAD
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.ForeignKey;
import javax.persistence.CascadeType;
import java.sql.Timestamp;
import java.time.Instant;
=======
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
>>>>>>> 3f4d31c (Unidirectional User->Role)

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE users SET soft_delete = TRUE WHERE id=?")
@Where(clause = "soft_delete = false")

public class User {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "user_id")
	private String id;
	
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String photo;

    private Timestamp timestamp = Timestamp.from(Instant.now());
<<<<<<< HEAD

    
    @JoinColumn(name = "role_id",nullable = false,foreignKey=@ForeignKey(name = "Fk_role_id"))
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    private Role roleId;
=======
/*
    @ManyToOne(fetch = FetchType.LAZY ,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "users",nullable = false)
    */
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
    private Role role;//changed "_"
>>>>>>> 3f4d31c (Unidirectional User->Role)
    

    @Column(name = "soft_delete")
    private boolean softDelete = Boolean.FALSE;


}
