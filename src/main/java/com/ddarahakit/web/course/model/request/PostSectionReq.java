package com.ddarahakit.web.course.model.request;


import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class PostSectionReq {
    // TODO: 입력값 검증 코드 추가할 것
    private String name;
    private List<PostLectureReq> lectures = new ArrayList<>();
}
