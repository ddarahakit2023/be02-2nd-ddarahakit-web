package com.ddarahakit.web.course.model.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PostCourseRes {
    // TODO: 섹션 및 강의에 대한 응답 코드도 추가할 것
    private Long id;

    private String name;

    private String image;

    private String description;

    private Integer price;
}
