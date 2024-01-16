package com.ddarahakit.web.member.model;

import com.ddarahakit.web.user.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserEntityTest {
    @Test
    @DisplayName("유저가 생성되는지 확인하는 테스트")
    void createMember(){
        User user = User.builder()
                .email("test01@test.com")
                .password("qwer1234")
                .name("test01")
                .build();

        assertThat(user.getEmail()).isEqualTo("test01@test.com");
    }
    @Test
    @DisplayName("유저의 이름이 바뀌는지 확인하는 테스트")
    void changeAgeTest(){
        User user = User.builder()
                .email("test01@test.com")
                .password("qwer1234")
                .name("test01")
                .build();
        user.setName("test02");
        assertThat(user.getName()).isEqualTo("test02");
    }
}
