package com.ddarahakit.web.course.service;

import com.ddarahakit.web.course.model.Course;
import com.ddarahakit.web.course.model.Lecture;
import com.ddarahakit.web.course.model.Section;
import com.ddarahakit.web.course.model.request.PostCourseReq;
import com.ddarahakit.web.course.model.request.PostLectureReq;
import com.ddarahakit.web.course.model.request.PostSectionReq;
import com.ddarahakit.web.course.model.response.GetCourseRes;
import com.ddarahakit.web.course.model.response.GetLectureRes;
import com.ddarahakit.web.course.model.response.GetSectionRes;
import com.ddarahakit.web.course.model.response.PostCourseRes;
import com.ddarahakit.web.course.repository.CourseRepository;
import com.ddarahakit.web.course.repository.LectureRepository;
import com.ddarahakit.web.course.repository.SectionRepository;
import com.ddarahakit.web.exception.ErrorCode;
import com.ddarahakit.web.exception.exception.CourseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final SectionRepository sectionRepository;
    private final LectureRepository lectureRepository;

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

        if (request.getSections() != null) {
            for (PostSectionReq postSectionReq : request.getSections()) {
                Section section = Section.builder()
                        .name(postSectionReq.getName())
                        .course(course)
                        .build();
                sectionRepository.save(section);
                if (postSectionReq.getLectures() != null) {
                    for (PostLectureReq postLectureReq : postSectionReq.getLectures()) {
                        Lecture lecture = Lecture.builder()
                                .name(postLectureReq.getName())
                                .playTime(postLectureReq.getPlayTime())
                                .videoUrl(postLectureReq.getVideoUrl())
                                .section(section)
                                .build();
                        lectureRepository.save(lecture);
                    }
                }

            }
        }
        return PostCourseRes.builder()
                .id(course.getId())
                .name(course.getName())
                .image(course.getImage())
                .description(course.getDescription())
                .price(course.getPrice())
                .build();
    }

    @Transactional
    public List<GetCourseRes> listCourse() {
        List<Course> courseList = courseRepository.findAll();
        List<GetCourseRes> getCourseResList = new ArrayList<>();

        for (Course course : courseList) {
            List<GetSectionRes> getSectionResList = new ArrayList<>();
            for (Section section : course.getSections()) {
                List<GetLectureRes> getLectureResList = new ArrayList<>();
                for (Lecture lecture : section.getLectures()) {

                    GetLectureRes getLectureRes = GetLectureRes.builder()
                            .id(lecture.getId())
                            .name(lecture.getName())
                            .playTime(lecture.getPlayTime())
                            .videoUrl(lecture.getVideoUrl())
                            .build();
                    getLectureResList.add(getLectureRes);
                }
                GetSectionRes getSectionRes = GetSectionRes.builder()
                        .id(section.getId())
                        .name(section.getName())
                        .getLectureResList(getLectureResList)
                        .build();
                getSectionResList.add(getSectionRes);
            }


            GetCourseRes getCourseRes = GetCourseRes.builder()
                    .id(course.getId())
                    .name(course.getName())
                    .description(course.getDescription())
                    .image(course.getImage())
                    .price(course.getPrice())
                    .getSectionResList(getSectionResList)
                    .build();

            getCourseResList.add(getCourseRes);
        }
        return getCourseResList;
    }


    @Transactional
    public GetCourseRes readCourse(Long id) {
        Optional<Course> result = courseRepository.findById(id);

        if (result.isPresent()) {
            Course course = result.get();
            List<GetSectionRes> getSectionResList = new ArrayList<>();
            for (Section section : course.getSections()) {
                List<GetLectureRes> getLectureResList = new ArrayList<>();
                for (Lecture lecture : section.getLectures()) {

                    GetLectureRes getLectureRes = GetLectureRes.builder()
                            .id(lecture.getId())
                            .name(lecture.getName())
                            .playTime(lecture.getPlayTime())
                            .videoUrl(lecture.getVideoUrl())
                            .build();
                    getLectureResList.add(getLectureRes);
                }
                GetSectionRes getSectionRes = GetSectionRes.builder()
                        .id(section.getId())
                        .name(section.getName())
                        .getLectureResList(getLectureResList)
                        .build();
                getSectionResList.add(getSectionRes);
            }


            GetCourseRes getCourseRes = GetCourseRes.builder()
                    .id(course.getId())
                    .name(course.getName())
                    .description(course.getDescription())
                    .image(course.getImage())
                    .price(course.getPrice())
                    .getSectionResList(getSectionResList)
                    .build();
            return getCourseRes;

        }
        return null;
    }
}