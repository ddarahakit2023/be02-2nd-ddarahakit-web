package com.ddarahakit.web.user.model.request;

import io.swagger.annotations.ApiParam;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostEditUserProfileImageReq {
    @ApiParam(value = "사용자 번호 입력", required = true, example = "1")
    @NotNull
    private Long id;
    @NotNull
    private MultipartFile image;

}
