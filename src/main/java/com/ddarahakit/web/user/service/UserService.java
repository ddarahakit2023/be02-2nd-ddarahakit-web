package com.ddarahakit.web.user.service;

import com.ddarahakit.web.exception.ErrorCode;
import com.ddarahakit.web.exception.exception.MemberException;
import com.ddarahakit.web.user.model.User;
import com.ddarahakit.web.user.model.request.PostSignupReq;
import com.ddarahakit.web.user.model.response.PostSignupRes;
import com.ddarahakit.web.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public PostSignupRes signup(PostSignupReq request) {
        Optional<User> result = userRepository.findByEmail(request.getEmail());

        if (result.isPresent()) {
            throw new MemberException(ErrorCode.DUPLICATED_USER, String.format("email is %s", request.getEmail()));
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
