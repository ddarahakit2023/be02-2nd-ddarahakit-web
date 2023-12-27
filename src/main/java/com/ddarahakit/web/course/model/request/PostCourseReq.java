package com.ddarahakit.web.course.model.request;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Min;

@Builder
@Data
public class PostCourseReq {
    private String name;

    private String image;

    private String description;

    private Integer price;
}
