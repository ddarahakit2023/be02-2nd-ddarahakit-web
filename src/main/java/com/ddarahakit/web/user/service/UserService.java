package com.ddarahakit.web.user.service;

import com.ddarahakit.web.aws.service.S3Service;
import com.ddarahakit.web.exception.ErrorCode;
import com.ddarahakit.web.exception.exception.MemberException;
import com.ddarahakit.web.user.model.User;
import com.ddarahakit.web.user.model.request.PostEditUserProfileImageReq;
import com.ddarahakit.web.user.model.request.PostLoginReq;
import com.ddarahakit.web.user.model.request.PostSignupReq;
import com.ddarahakit.web.user.model.request.PutEditUserProfileReq;
import com.ddarahakit.web.user.model.response.PostEditUserProfileImageRes;
import com.ddarahakit.web.user.model.response.PostLoginRes;
import com.ddarahakit.web.user.model.response.PostSignupRes;
import com.ddarahakit.web.user.model.response.PutEditUserProfileRes;
import com.ddarahakit.web.user.repository.UserRepository;
import com.ddarahakit.web.utils.ImageUtils;
import com.ddarahakit.web.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final S3Service s3Service;

    public PostSignupRes signup(PostSignupReq request) {
        Optional<User> result = userRepository.findByEmail(request.getEmail());

        if (result.isPresent()) {
            throw new MemberException(ErrorCode.DUPLICATED_USER, String.format("email is %s", request.getEmail()));
        }

        User user = User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        user = userRepository.save(user);

        return PostSignupRes.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }

    public PostSignupRes signupTeacher(PostSignupReq request) {
        Optional<User> result = userRepository.findByEmail(request.getEmail());

        if (result.isPresent()) {
            User user = result.get();
            if(user.getAuthority().contains("ROLE_TEACHER")) {
                throw new MemberException(ErrorCode.DUPLICATED_USER, String.format("email is %s", request.getEmail()));
            } else {
                user.setAuthority(user.getAuthority()+",ROLE_TEACHER");
                user = userRepository.save(user);
                return PostSignupRes.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .name(user.getName())
                        .build();
            }
        }

        User user = User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .authority("ROLE_TEACHER")
                .build();

        user = userRepository.save(user);

        return PostSignupRes.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }

    public PutEditUserProfileRes editUserProfile(PutEditUserProfileReq request) {
        Optional<User> result = userRepository.findById(request.getId());

        if (result.isPresent()) {
            User user = result.get();
            if (request.getEmail() != null) {
                user.setEmail(request.getEmail());
            }
            if (request.getName() != null) {
                user.setName(request.getName());
            }
            if (request.getPassword() != null) {
                user.setPassword(request.getPassword());
            }

            user = userRepository.save(user);

            return PutEditUserProfileRes.toDto(user);
        }
        return null;

    }

    public PostEditUserProfileImageRes editUserProfileImage(PostEditUserProfileImageReq request) {
        Optional<User> result = userRepository.findById(request.getId());

        if (result.isPresent()) {
            User user = result.get();

            if (request.getImage() != null) {
                String savePath = ImageUtils.makeImagePath(request.getImage().getOriginalFilename());
                savePath = s3Service.uploadFile(request.getImage(), savePath);
                user.setProfileImage(savePath);
            } else {
                throw new MemberException(ErrorCode.PROFILE_IMAGE_EMPTY);
            }

            user = userRepository.save(user);
            return PostEditUserProfileImageRes.toDto(user);
        }
        return null;
    }

    public PostLoginRes login(PostLoginReq request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = (User) authentication.getPrincipal();
        String accessToken = JwtTokenUtils.generateAccessToken(user);
        return PostLoginRes.builder().accessToken(accessToken).build();
    }
}
