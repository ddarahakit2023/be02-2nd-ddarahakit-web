package com.ddarahakit.web.course.service;

import com.ddarahakit.web.course.model.Course;
import com.ddarahakit.web.course.model.request.PostCourseReq;
import com.ddarahakit.web.course.model.response.PostCourseRes;
import com.ddarahakit.web.course.repository.CourseRepository;
import com.ddarahakit.web.exception.ErrorCode;
import com.ddarahakit.web.exception.exception.CourseException;
import com.ddarahakit.web.exception.exception.MemberException;
import com.ddarahakit.web.user.model.User;
import com.ddarahakit.web.user.model.request.PostSignupReq;
import com.ddarahakit.web.user.model.response.PostSignupRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CourseService {
    private final CourseRepository courseRepository;


    public PostCourseRes createCourse(PostCourseReq request) {
        Optional<Course> result = courseRepository.findByName(request.getName());

        if (result.isPresent()) {
            throw new CourseException(ErrorCode.DUPLICATED_COURSE, String.format("name is %s", request.getName()));
        }

        Course course = Course.builder()
                .name(request.getName())
                .image(request.getImage())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();

        course = courseRepository.save(course);

        return PostCourseRes.builder()
                .id(course.getId())
                .name(course.getName())
                .image(course.getImage())
                .description(course.getDescription())
                .price(course.getPrice())
                .build();
    }
}
