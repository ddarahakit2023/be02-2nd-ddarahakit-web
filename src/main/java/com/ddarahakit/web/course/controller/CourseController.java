package com.ddarahakit.web.course.controller;

import com.ddarahakit.web.course.model.request.PostCourseReq;
import com.ddarahakit.web.course.model.response.PostCourseRes;
import com.ddarahakit.web.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity createCourse(@RequestBody PostCourseReq request) {
        PostCourseRes response = courseService.createCourse(request);

        return ResponseEntity.ok().body(response);
    }

}
