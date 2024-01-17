package com.ddarahakit.web.order.model;

import com.ddarahakit.web.user.model.User;
import lombok.*;

import javax.persistence.*;
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
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false, nullable = false)
    private Date createdAt;

    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "User_id")
    private User user;

    @OneToMany(mappedBy = "orders")
    List<OrderCourse> orderCourseList = new ArrayList<>();

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
