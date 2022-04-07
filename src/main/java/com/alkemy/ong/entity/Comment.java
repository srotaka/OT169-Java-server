package com.alkemy.ong.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "comments")
@SQLDelete(sql = "UPDATE activities SET soft_delete = true WHERE id=?")
@Where(clause = "soft_delete = false")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Comment {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Lob
    @Column(nullable = false)
    private String body;

    @ManyToOne(fetch = FetchType.EAGER ,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "user_id",nullable = false,foreignKey=@ForeignKey(name = "FK_user"))
    private User user_id;

    @ManyToOne(fetch = FetchType.EAGER ,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "news_id",nullable = false,foreignKey=@ForeignKey(name = "FK_news"))
    private News newsId;

    private Timestamp timestamp = Timestamp.from(Instant.now());

    private boolean softDelete = Boolean.FALSE;


}
