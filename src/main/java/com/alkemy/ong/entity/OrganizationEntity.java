package com.alkemy.ong.entity;


import lombok.*;
import org.hibernate.annotations.SQLDelete;
import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name="organizations")
@Setter
@Getter
@SQLDelete(sql = "UPDATE organizations SET soft_delete  = true WHERE id = ?")
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID organizationId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String image;

    @Column
    private String address;

    @Column
    private Long phone;

    @Column(nullable = false)
    private String email;

    @Column(name = "welcome_text", nullable = false)
    private String welcomeText;

    @Column(name = "about_us_text")
    private String aboutUsText;

    private Timestamp timestamp = Timestamp.from(Instant.now());

    @Column(name="soft_delete")
    private Boolean softDelete = Boolean.FALSE;


}
