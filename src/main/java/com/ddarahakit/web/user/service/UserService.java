package com.ddarahakit.web.user.service;

import com.ddarahakit.web.user.model.User;
import com.ddarahakit.web.user.model.request.PostSignupReq;
import com.ddarahakit.web.user.model.response.PostSignupRes;
import com.ddarahakit.web.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public PostSignupRes signup(PostSignupReq request) {
        Optional<User> result = userRepository.findByEmail(request.getEmail());

        if (result.isPresent()) {
            System.out.println("중복된 이메일 존재");
            return null;
        }

        User user = User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(request.getPassword())
                .build();

        user = userRepository.save(user);

        return PostSignupRes.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }


}
