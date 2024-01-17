package com.ddarahakit.web.course.controller;

import com.ddarahakit.web.course.model.request.PostCourseReq;
import com.ddarahakit.web.course.model.response.GetCourseRes;
import com.ddarahakit.web.course.model.response.PostCourseRes;
import com.ddarahakit.web.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity listCourse() {
        List<GetCourseRes> getCourseResList = courseService.listCourse();

        return ResponseEntity.ok().body(getCourseResList);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity readCourse(@PathVariable Long id) {
        GetCourseRes getCourseRes = courseService.readCourse(id);

        return ResponseEntity.ok().body(getCourseRes);
    }

}
