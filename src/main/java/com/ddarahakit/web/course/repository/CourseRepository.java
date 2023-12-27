package com.ddarahakit.web.course.repository;

import com.ddarahakit.web.course.model.Course;
import com.ddarahakit.web.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    public Optional<Course> findByName(String name);

}
