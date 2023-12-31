package com.ddarahakit.web.course.repository;

import com.ddarahakit.web.course.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {

}
