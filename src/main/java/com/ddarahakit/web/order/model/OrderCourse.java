package com.ddarahakit.web.order.model;

import com.ddarahakit.web.course.model.Course;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false, nullable = false)
    private Date createdAt;

    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "Orders_id")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "Course_id")
    private Course course;

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
