package com.ddarahakit.web.member.service;

import com.ddarahakit.web.aws.service.S3Service;
import com.ddarahakit.web.user.model.User;
import com.ddarahakit.web.user.model.request.PostSignupReq;
import com.ddarahakit.web.user.model.response.PostSignupRes;
import com.ddarahakit.web.user.repository.UserRepository;
import com.ddarahakit.web.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    // Test 주체
    UserService userService;

    // Test 협력자
    @MockBean
    UserRepository userRepository;

    @MockBean
    S3Service s3Service;


    // Test를 실행하기 전마다 MemberService에 가짜 객체를 주입시켜준다.
    @BeforeEach
    void setUp(){
        userService = new UserService(userRepository, s3Service);
    }

    @Test
    @DisplayName("유저 생성 성공")
    void createMemberSuccess(){
    /*
    given
     */
        User user = User.builder()
                .email("test01@test.com")
                .password("qwer1234")
                .name("test01")
                .build();
        ReflectionTestUtils.setField(user,"id",3l);

        Mockito.when(userRepository.save(user)).thenReturn(user); // 가짜 객체 응답 정의
    /*
    when
     */
        PostSignupRes result = userService.signup(PostSignupReq.builder().build());
    /*
    then
     */
        assertThat(result.getId()).isEqualTo(3L);
    }
}