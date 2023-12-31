package com.ddarahakit.web.course.model.request;

import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class PostCourseReq {
    @ApiParam(value = "과정 이름 입력", required = true, example = "따라하면서 배우는 자바 기초")
    @NotNull
    private String name;

    @ApiParam(value = "과정의 대표 이미지 입력", required = true, example = "이미지 파일 업로드")
    private String image;

    @ApiParam(value = "과정에 대한 간단한 설명 입력", required = true, example = "따라하면서 배우는 자바 기초 과정은 기본적인 자바 문법에 대해서 학습하는 과정입니다.")
    @NotNull
    private String description;

    @ApiParam(value = "과정의 가격을 입력", required = true, example = "20000")
    @NotNull
    @PositiveOrZero
    private Integer price;

    private List<PostSectionReq> sections = new ArrayList<>();
}
