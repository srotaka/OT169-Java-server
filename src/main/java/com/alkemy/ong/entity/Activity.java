package com.alkemy.ong.entity;

import java.sql.Timestamp;

import java.time.Instant;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.NonNull;


@Entity
@Table(name = "activities")
@SQLDelete(sql = "UPDATE activities SET soft_delete = true WHERE id=?")
@Where(clause = "soft_delete = false")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Activity {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@NonNull
	@Column(unique = true)
	private String name;
	@NonNull
	@Lob
	private String content;
	@NonNull
	private String image;
	private Timestamp timestamp = Timestamp.from(Instant.now());
	private boolean softDelete = Boolean.FALSE;

}
