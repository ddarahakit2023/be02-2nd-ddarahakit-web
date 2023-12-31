package com.ddarahakit.web.course.model.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PostLectureReq {
    // TODO: 입력값 검증 코드 추가할 것
    private String name;
    private Integer playTime;
    private String videoUrl;

}
