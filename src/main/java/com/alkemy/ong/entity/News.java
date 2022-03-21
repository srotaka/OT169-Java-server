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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author Franco Lamberti
 */

@Entity
@Table(name = "news")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class News {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	@Column(nullable = false)
	@NotNull(message = "Name cannot be null")
	private String name;
	
	@Column(nullable = false, columnDefinition="TEXT", length = 65535)
	@NotNull(message = "Content cannot be null")
	private String content;
	
	@Column(nullable = false)
	@NotNull(message = "Image cannot be null")
	private String image;
	
	@OneToMany
	@JoinColumn(name="Category_ID")
	private List<Category> categories;
	
	private Timestamp timestamp = Timestamp.from(Instant.now());
	
    private boolean softDelete = false;
	
    public News() {    	
    }

	public News(String id, @NotNull(message = "Name cannot be null") String name, String content, String image,
			Timestamp timestamp, boolean softDelete) {
		super();
		this.id = id;
		this.name = name;
		this.content = content;
		this.image = image;
		this.timestamp = timestamp;
		this.softDelete = softDelete;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean isSoftDelete() {
		return softDelete;
	}

	public void setSoftDelete(boolean softDelete) {
		this.softDelete = softDelete;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
    
}
