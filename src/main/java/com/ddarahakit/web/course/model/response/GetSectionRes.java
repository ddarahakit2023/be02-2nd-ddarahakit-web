package com.ddarahakit.web.course.model.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class GetSectionRes {
    private Long id;
    private String name;

    List<GetLectureRes> getLectureResList;
}
