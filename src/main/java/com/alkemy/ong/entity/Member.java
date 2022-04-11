package com.alkemy.ong.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "Details about the Member")
public class Member {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@ApiModelProperty(notes = "The unique id of the Member")
	private String id;

	@Column(nullable = false)
	@ApiModelProperty(notes = "The Member's name",required = true,position = 1)
	private String name;

	@ApiModelProperty(notes = "The Member's facebook url",position = 2)
	private String facebookUrl;

	@ApiModelProperty(notes = "The Member's instagram url",position = 3)
	private String instagramUrl;

	@ApiModelProperty(notes = "The Member's instagram url",position = 4)
	private String linkedinUrl;

	@Column(nullable = false)
	@ApiModelProperty(notes = "The Member's image",required = true,position = 5)
	private String image;

	@ApiModelProperty(notes = "The Member's description",position = 6)
	private String description;

	@ApiModelProperty(notes = "The Member's timestamp creation",position = 7)
	private Timestamp timestamp = Timestamp.from(Instant.now());

	@Column(name = "soft_delete")
	@ApiModelProperty(notes = "The Member's logic delete flag",position = 8)
	private boolean softDelete = Boolean.FALSE;
}
