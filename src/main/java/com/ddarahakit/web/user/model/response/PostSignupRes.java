package com.ddarahakit.web.user.model.response;

import com.ddarahakit.web.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PostSignupRes {
    private Long id;
    private String email;
    private String name;
}
