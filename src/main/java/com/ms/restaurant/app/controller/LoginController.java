package com.ms.restaurant.app.controller;

import com.ms.restaurant.app.dto.AdminLoginReqDto;
import com.ms.restaurant.app.dto.UserLoginReqDto;
import com.ms.restaurant.app.dto.UserLoginResDto;
import com.ms.restaurant.app.service.LoginService;
import com.ms.restaurant.dto.responseDto.ResponseDto;
import com.ms.restaurant.dto.responseDto.SuccessResponseDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Slf4j
@Validated
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/admin")
    public ResponseDto<?> adminLogin(@RequestBody @Valid AdminLoginReqDto adminLoginReq) {
        UserLoginResDto userResDto = loginService.adminLogin(adminLoginReq);
        return new SuccessResponseDto<>(userResDto, "Admin Login Successfully", HttpStatus.OK);
    }

    @PostMapping("/findByUsername")
    public ResponseDto<?> findUser(@RequestParam @Valid String username) {
        UserLoginResDto userResDto = loginService.findByUsername(username);
        return new SuccessResponseDto<>(userResDto, "OTP send Successfully", HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseDto<?> userLogin(@RequestBody @Valid UserLoginReqDto userLoginReq) {
        UserLoginResDto userResDto = loginService.userLogin(userLoginReq);
        return new SuccessResponseDto<>(userResDto, "User Login Successfully", HttpStatus.OK);
    }

    @PostMapping("/sendOtp")
    public ResponseDto<?> sendOtp(@RequestParam @Valid String username) {
        UserLoginResDto userResDto = loginService.sendOtp(username);
        return new SuccessResponseDto<>(userResDto, "OTP send Successfully", HttpStatus.OK);
    }

    @PostMapping("/verifyOtp")
    public ResponseDto<?> verifyOtp(@RequestParam @Valid String username, @RequestParam @Valid String otp) {
        UserLoginResDto userResDto = loginService.verifyOtp(username, otp);
        return new SuccessResponseDto<>(userResDto, "OTP verified Successfully", HttpStatus.OK);
    }

    @PostMapping("signup")
    public ResponseDto<?> signup(@RequestBody @Valid AdminLoginReqDto adminLoginReqDto) {
        UserLoginResDto userResDto = loginService.signup(adminLoginReqDto);
        return new SuccessResponseDto<>(userResDto, "User Signup Successfully", HttpStatus.OK);
    }

}
