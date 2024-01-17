package com.ddarahakit.web.course.service;

import com.ddarahakit.web.course.model.Course;
import com.ddarahakit.web.course.model.Lecture;
import com.ddarahakit.web.course.model.response.GetLectureRes;
import com.ddarahakit.web.course.repository.LectureRepository;
import com.ddarahakit.web.order.model.OrderCourse;
import com.ddarahakit.web.order.model.Orders;
import com.ddarahakit.web.order.repository.OrderRepository;
import com.ddarahakit.web.order.service.OrderService;
import com.ddarahakit.web.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;
    private final OrderService orderService;

    @Transactional
    public GetLectureRes readLecture(User user, Long courseId, Long lectureId) {
        Boolean orderCheckResult = orderService.checkOrderCourse(user, courseId);

        if (orderCheckResult) {
            Optional<Lecture> result = lectureRepository.findById(lectureId);
            if(result.isPresent()) {
                Lecture lecture = result.get();
                return GetLectureRes.builder()
                        .id(lecture.getId())
                        .name(lecture.getName())
                        .playTime(lecture.getPlayTime())
                        .videoUrl(lecture.getVideoUrl())
                        .build();
            }
        }
        return null;
    }
}
