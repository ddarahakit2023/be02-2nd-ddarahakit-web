package com.ddarahakit.web.course.model.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class GetCourseRes {
    private Long id;
    private String name;
    private String description;
    private String image;
    private Integer price;
    List<GetSectionRes> getSectionResList;
}
