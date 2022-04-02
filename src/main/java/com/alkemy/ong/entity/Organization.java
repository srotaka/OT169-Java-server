package com.alkemy.ong.entity;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name="organizations")
@SQLDelete(sql = "UPDATE organizations SET soft_delete  = true WHERE id = ?")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Organization {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String image;

    private String address;

    private Long phone;

    @Column(nullable = false)
    private String email;

    @Column(name = "welcome_text", nullable = false)
    private String welcomeText;

    @Column(name = "about_us_text")
    private String aboutUsText;

    private String facebookUrl;

    private String linkedinUrl;

    private String instagramUrl;

    private Timestamp timestamp = Timestamp.from(Instant.now());

    @Column(name="soft_delete")
    private Boolean softDelete = Boolean.FALSE;


}
