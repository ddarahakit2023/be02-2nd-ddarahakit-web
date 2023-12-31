package com.ddarahakit.web.course.model.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PostLectureReq {
    private String name;
    private Integer playTime;
    private String videoUrl;

}
