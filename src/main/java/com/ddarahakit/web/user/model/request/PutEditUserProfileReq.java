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
public class PutEditUserProfileReq {
    @ApiParam(value = "사용자 번호 입력", required = true, example = "1")
    @NotNull
    private Long id;

    @ApiParam(value = "이메일 입력", example = "test01@test.com")
    @Pattern(regexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
    private String email;

    @ApiParam(value = "비밀번호 입력", example = "Dkagh1234!")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$")
    private String password;

    @ApiParam(value = "사용자 이름 입력", example = "홍길동")
    @Pattern(regexp = "[가-힣0-9]{2,5}|[a-zA-Z]{2,10}\\s[a-zA-Z]{2,10}$")
    private String name;

}
