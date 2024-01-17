package com.ddarahakit.web.course.model;

import com.ddarahakit.web.user.model.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.lang.reflect.Member;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String name;

    @Column(nullable = false, length = 200)
    private String image;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Min(0)
    private Integer price;

    @ColumnDefault("1")
    private Boolean isDisplay;

    @ManyToOne
    @JoinColumn(name = "User_id")
    private User user;

    @Column(updatable = false, nullable = false)
    private Date createdAt;

    private Date updatedAt;


    @OneToMany(mappedBy = "course")
    private List<Section> sections = new ArrayList<>();

    @PrePersist
    void createdAt() {
        this.createdAt = Timestamp.from(Instant.now());
        this.updatedAt = Timestamp.from(Instant.now());
    }

    @PreUpdate
    void updatedAt() {
        this.updatedAt = Timestamp.from(Instant.now());
    }


}
