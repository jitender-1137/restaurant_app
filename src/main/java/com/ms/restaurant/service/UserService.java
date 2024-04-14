package com.ms.restaurant.service;

import com.ms.restaurant.dto.requestDto.ResetPasswordReqDto;
import com.ms.restaurant.dto.requestDto.UpdatePasswordReqDto;
import com.ms.restaurant.dto.requestDto.UserRequestDto;
import com.ms.restaurant.dto.requestDto.UserUpdateRequestDto;
import com.ms.restaurant.dto.responseDto.AuthResponseDto;
import com.ms.restaurant.dto.responseDto.UserResponseDto;
import jakarta.validation.Valid;

import java.util.List;

public interface UserService {

    UserResponseDto updateUser(@Valid UserUpdateRequestDto userUpdateRequestDto);

    UserResponseDto getUser(Long id);

    void deleteUser(Long id);

    UserResponseDto getByUsername(String username);

    List<UserResponseDto> getAllUser(Integer perPage, Integer page);

    void reSendOtp(String username);

//    void accountVerification(String username, String otp);

//    void updatePassword(@Valid UpdatePasswordReqDto updatePasswordReqDto);
//
//    void forgotPassword(String username);
//
//    void resetPassword(ResetPasswordReqDto resetPasswordReqDto);
}
