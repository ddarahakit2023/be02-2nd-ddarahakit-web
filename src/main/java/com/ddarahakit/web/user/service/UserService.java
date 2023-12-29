package com.ddarahakit.web.user.service;

import com.ddarahakit.web.aws.service.S3Service;
import com.ddarahakit.web.exception.ErrorCode;
import com.ddarahakit.web.exception.exception.MemberException;
import com.ddarahakit.web.user.model.User;
import com.ddarahakit.web.user.model.request.PostEditUserProfileImageReq;
import com.ddarahakit.web.user.model.request.PostSignupReq;
import com.ddarahakit.web.user.model.request.PutEditUserProfileReq;
import com.ddarahakit.web.user.model.response.PostSignupRes;
import com.ddarahakit.web.user.repository.UserRepository;
import com.ddarahakit.web.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final S3Service s3Service;
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
                .name(user.getName())
                .build();
    }


    public void editUserProfile(PutEditUserProfileReq request) {
        Optional<User> result = userRepository.findById(request.getId());

        if(result.isPresent()) {
            User user = result.get();
            if(request.getEmail() != null) {
                user.setEmail(request.getEmail());
            }
            if(request.getName() != null) {
                user.setName(request.getName());
            }
            if(request.getPassword() != null) {
                user.setPassword(request.getPassword());
            }

            userRepository.save(user);
        }
    }

    public void editUserProfileImage(PostEditUserProfileImageReq request) {
        Optional<User> result = userRepository.findById(request.getId());

        if(result.isPresent()) {
            User user = result.get();

            if(request.getImage() != null) {
                String savePath = ImageUtils.makeImagePath(request.getImage().getOriginalFilename());
                savePath = s3Service.uploadFile(request.getImage(), savePath);
                user.setProfileImage(savePath);
            } else {
                throw new MemberException(ErrorCode.PROFILE_IMAGE_EMPTY);
            }

            userRepository.save(user);
        }
    }
}
