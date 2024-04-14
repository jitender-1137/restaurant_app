package com.ms.restaurant.service;

import com.ms.restaurant.dto.requestDto.AdminAuthRequestDto;
import com.ms.restaurant.dto.requestDto.UserAuthRequestDto;
import com.ms.restaurant.dto.responseDto.AuthResponseDto;

public interface AuthService {

    void addSuperUser();

    AuthResponseDto adminLogin(AdminAuthRequestDto adminAuthRequestDto);

    AuthResponseDto userLogin(UserAuthRequestDto userAuthRequestDto);
}
