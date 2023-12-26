package com.ddarahakit.web.user.controller;

import com.ddarahakit.web.user.model.request.PostSignupReq;
import com.ddarahakit.web.user.model.response.PostSignupRes;
import com.ddarahakit.web.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "회원 컨트롤러", tags = "회원 API")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;
    @ApiOperation(value = "회원가입")
    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity signup(@Valid @RequestBody PostSignupReq request) {
        PostSignupRes response = userService.signup(request);
        return ResponseEntity.ok().body(response);
    }
}
