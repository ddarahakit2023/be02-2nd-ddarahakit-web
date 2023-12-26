package com.ddarahakit.web.user.model.request;

import io.swagger.annotations.ApiParam;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostSignupReq {
    @ApiParam(value = "이메일 입력", required = true, example = "test01@test.com")
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
    private String email;

    @ApiParam(value = "비밀번호 입력", required = true, example = "Dkagh1234!")
    @NotNull
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$")
    private String password;

    @NotNull
    @Pattern(regexp = "[가-힣0-9]{2,5}|[a-zA-Z]{2,10}\\s[a-zA-Z]{2,10}$")
    private String name;
}
