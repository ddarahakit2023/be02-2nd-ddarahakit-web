package com.ddarahakit.web.member.repository;

import com.ddarahakit.web.user.model.User;
import com.ddarahakit.web.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("유저 만들기")
    void createMember(){
        /*
        given
         */
        User user = User.builder()
                .email("test01@test.com")
                .password("qwer1234")
                .name("test01")
                .build();
        /*
        when
         */
        User result = userRepository.save(user);

        /*
        then
         */
        assertThat(result.getEmail()).isEqualTo(user.getEmail());


    }

    @Test
    @DisplayName("멤버의 리스트를 반환 하는지 확인")
    void MemberList(){
        /*
        given
         */
        User user1 = User.builder()
                .email("test01@test.com")
                .password("qwer1234")
                .name("test01")
                .build();

        User user2 = User.builder()
                .email("test02@test.com")
                .password("qwer1234")
                .name("test02")
                .build();
        userRepository.save(user1);
        userRepository.save(user2);
 
        /*
        when
         */
        List<User> result = userRepository.findAll();
 
        /*
        then
         */
        assertThat(result.size()).isEqualTo(2);
    }

}
