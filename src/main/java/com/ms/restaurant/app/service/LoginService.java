package com.ms.restaurant.app.service;

import com.ms.restaurant.app.dto.AdminLoginReqDto;
import com.ms.restaurant.app.dto.UserLoginReqDto;
import com.ms.restaurant.app.dto.UserLoginResDto;

public interface LoginService {
    UserLoginResDto adminLogin(AdminLoginReqDto adminLoginReq);

    UserLoginResDto findByUsername(String username);

    UserLoginResDto userLogin(UserLoginReqDto userLoginReq);

    UserLoginResDto sendOtp(String username);

    UserLoginResDto verifyOtp(String username, String otp);

    UserLoginResDto signup(AdminLoginReqDto adminLoginReqDto);
}
