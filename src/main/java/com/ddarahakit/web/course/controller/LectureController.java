package com.ddarahakit.web.course.controller;

import com.ddarahakit.web.course.model.response.GetLectureRes;
import com.ddarahakit.web.course.service.LectureService;
import com.ddarahakit.web.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lecture")
public class LectureController {
    private final LectureService lectureService;

    @RequestMapping(method = RequestMethod.GET, value = "/{courseId}/{lectureId}")
    public GetLectureRes readLecture(@AuthenticationPrincipal User user, @PathVariable Long courseId, @PathVariable Long lectureId) {
        return lectureService.readLecture(user, courseId, lectureId);
    }
}
