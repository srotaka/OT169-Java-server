package com.alkemy.ong.entity;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

/**
 * @author Franco Lamberti
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "news")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@SQLDelete(sql = "UPDATE news SET soft_delete = TRUE WHERE id=?")
@Where(clause = "soft_delete = false")
@ApiModel(description = "Details about the New")
public class News {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@ApiModelProperty(notes = "The unique id of the New")
	private String id;
	
	@Column(nullable = false)
	@NotNull(message = "Name cannot be null")
	@ApiModelProperty(notes = "The New's name",required = true,position = 1)
	private String name;
	
	@Column(nullable = false, columnDefinition="TEXT", length = 65535)
	@NotNull(message = "Content cannot be null")
	@ApiModelProperty(notes = "The New's content",required = true,position = 2)
	private String content;
	
	@Column(nullable = false)
	@NotNull(message = "Image cannot be null")
	@ApiModelProperty(notes = "The New's image",required = true,position = 3)
	private String image;
	
	@OneToMany
	@JoinColumn(name="Category_ID")
	@Cascade(CascadeType.ALL)
	@ApiModelProperty(notes = "The New's list of categories",required = true,position = 4)
	private List<Category> categories;

	@ApiModelProperty(notes = "The New's timestamp creation",position = 5)
	private Timestamp timestamp = Timestamp.from(Instant.now());

	@Column(name = "soft_delete")
	@ApiModelProperty(notes = "The New's logic delete flag",position = 6)
    private boolean softDelete = false;
	

    
}
