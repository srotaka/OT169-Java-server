package com.alkemy.ong.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

<<<<<<< HEAD
=======
import javax.persistence.Column;
>>>>>>> 3f4d31c (Unidirectional User->Role)
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
<<<<<<< HEAD
@SQLDelete(sql = "UPDATE role SET softDeleted = true WHERE id=?")
@Where(clause="deleted=false")
=======
@SQLDelete(sql = "UPDATE role SET soft_deleted = true WHERE id=?")
@Where(clause="soft_deleted = false")

>>>>>>> 3f4d31c (Unidirectional User->Role)
public class Role {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "role_id")
    private String id;

    private String name;

    private String description;

    //@Temporal(TemporalType.TIMESTAMP) //The code doesn't compile with this annotation. Updated by Franco Lamberti
    private Date timestamps;

    private boolean softDeleted = Boolean.FALSE;

<<<<<<< HEAD
=======
   /*
    @OneToMany(
			mappedBy = "role",
			cascade = CascadeType.ALL,
			orphanRemoval = true
			)			
			private List<User> users;
    */
    
    
    
>>>>>>> 3f4d31c (Unidirectional User->Role)
    //Added by Franco Lamberti
    public Role(String id) {
    	this.id = id;
    }

}