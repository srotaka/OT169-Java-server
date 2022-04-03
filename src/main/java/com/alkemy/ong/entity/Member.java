package com.alkemy.ong.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "members")
@SQLDelete(sql = "UPDATE members SET soft_delete = TRUE WHERE id=?")
@Where(clause = "soft_delete = false")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Column(nullable = false)
	private String name;

	private String facebookUrl;

	private String instagramUrl;

	private String linkedinUrl;

	@Column(nullable = false)
	private String image;

	private String description;

	private Timestamp timestamp = Timestamp.from(Instant.now());

	@Column(name = "soft_delete")
	private boolean softDelete = Boolean.FALSE;
}
