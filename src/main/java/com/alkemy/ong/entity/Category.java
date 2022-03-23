package com.alkemy.ong.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "categories")
@Getter
@Setter
@SQLDelete(sql = "UPDATE categories SET soft_delete = true WHERE id=?")
@Where(clause = "soft_delete=false")
public class Category {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @NonNull
    private String name;
    @Nullable
    private String description;
    @Nullable
    private String image;
    private Timestamp timestamp = Timestamp.from(Instant.now());
    @Column(name = "soft_delete")
    private boolean softDelete = Boolean.FALSE;

    public Category(String name, String description, String image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }
}
