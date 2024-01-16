package com.ddarahakit.web.user.service;

import com.ddarahakit.web.aws.service.S3Service;
import com.ddarahakit.web.exception.ErrorCode;
import com.ddarahakit.web.exception.exception.MemberException;
import com.ddarahakit.web.user.model.User;
import com.ddarahakit.web.user.model.request.PostEditUserProfileImageReq;
import com.ddarahakit.web.user.model.request.PostSignupReq;
import com.ddarahakit.web.user.model.request.PutEditUserProfileReq;
import com.ddarahakit.web.user.model.response.PostEditUserProfileImageRes;
import com.ddarahakit.web.user.model.response.PostSignupRes;
import com.ddarahakit.web.user.model.response.PutEditUserProfileRes;
import com.ddarahakit.web.user.repository.UserRepository;
import com.ddarahakit.web.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> result = userRepository.findByEmail(username);
        if (result.isPresent()) {
            return result.get();
        }

        return null;
    }
}
