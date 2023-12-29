package com.ddarahakit.web.user.controller;

import com.ddarahakit.web.user.model.request.PostEditUserProfileImageReq;
import com.ddarahakit.web.user.model.request.PostSignupReq;
import com.ddarahakit.web.user.model.request.PutEditUserProfileReq;
import com.ddarahakit.web.user.model.response.PostEditUserProfileImageRes;
import com.ddarahakit.web.user.model.response.PostSignupRes;
import com.ddarahakit.web.user.model.response.PutEditUserProfileRes;
import com.ddarahakit.web.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Api(value = "회원 컨트롤러", tags = "회원 API")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;

    @ApiOperation(value = "회원가입")
    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity signup(@Valid @RequestBody PostSignupReq request) {
        PostSignupRes response = userService.signup(request);
        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "회원 프로필 정보 수정")
    @RequestMapping(method = RequestMethod.PUT, value = "/edit/profile")
    public ResponseEntity editUserProfile(@Valid @RequestBody PutEditUserProfileReq request) {
        PutEditUserProfileRes response = userService.editUserProfile(request);
        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "회원 프로필 이미지 수정")
    @RequestMapping(method = RequestMethod.POST, value = "/edit/profile/image")
    public ResponseEntity editUserProfileImage(PostEditUserProfileImageReq request) {
        PostEditUserProfileImageRes response = userService.editUserProfileImage(request);
        return ResponseEntity.ok().body(response);
    }
}
