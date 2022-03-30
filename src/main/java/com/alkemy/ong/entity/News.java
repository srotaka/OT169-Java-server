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
	@Cascade(CascadeType.ALL)
	private List<Category> categories;
	
	private Timestamp timestamp = Timestamp.from(Instant.now());

	@Column(name = "soft_delete")
    private boolean softDelete = false;
	

    
}
