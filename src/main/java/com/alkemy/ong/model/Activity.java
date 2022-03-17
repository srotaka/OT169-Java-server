package com.alkemy.ong.model;

import java.sql.Timestamp;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "activities")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE activities SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class Activity {
	
	@NonNull
	private String name;
	@NonNull
	private String content;
	@NonNull
	private String image;
	private Timestamp timestamp = Timestamp.from(Instant.now());
	private boolean softDelete = Boolean.FALSE;
	
	public Timestamp getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	public boolean isSoftDeleted() {
		return softDelete;
	}
	
	public void setSoftDelete(boolean softDelete) {
		this.softDelete = softDelete;
	}
}
