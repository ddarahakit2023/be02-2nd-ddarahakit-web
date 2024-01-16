package com.ddarahakit.web.user.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PostLoginRes {
    private String accessToken;
    private String refreshToken;
}
