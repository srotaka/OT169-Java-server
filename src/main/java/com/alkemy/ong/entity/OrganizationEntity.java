package com.alkemy.ong.entity;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="organizations")
@Setter
@Getter
@SQLDelete(sql = "UPDATE organizations SET is_deleted  = true WHERE id = ?")
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationEntity {

    @Id
    @Column(updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long organizationId;

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

    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "creation_date", updatable = false)
    private LocalDateTime creationDate;

    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name="is_deleted")
    private Boolean isDeleted = Boolean.FALSE;


}
