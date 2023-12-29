package com.ddarahakit.web.user.model.response;

import com.ddarahakit.web.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PostEditUserProfileImageRes {
    private Long id;
    private String email;
    private String name;
    private String profileImage;

    public static PostEditUserProfileImageRes toDto(User user){
        return PostEditUserProfileImageRes.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .profileImage(user.getProfileImage())
                .build();
    }
}
