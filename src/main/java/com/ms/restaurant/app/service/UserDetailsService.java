package com.ms.restaurant.app.service;

import com.ms.restaurant.app.dto.UserDetailsReqDto;
import com.ms.restaurant.app.dto.UserDetailsResDto;

import java.util.List;

public interface UserDetailsService {
    UserDetailsResDto getUser(Long id);

    List<UserDetailsResDto> getAllUsers();

    UserDetailsResDto saveUserDetails(UserDetailsReqDto userDetailsReqDto);

    UserDetailsResDto updateUserProfile(UserDetailsReqDto userDetailsReqDto);

    void deleteUser(Long id);
}
