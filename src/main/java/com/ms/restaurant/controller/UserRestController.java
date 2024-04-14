package com.ms.restaurant.controller;

import com.ms.restaurant.dto.requestDto.UserUpdateRequestDto;
import com.ms.restaurant.dto.responseDto.ResponseDto;
import com.ms.restaurant.dto.responseDto.SuccessResponseDto;
import com.ms.restaurant.dto.responseDto.UserResponseDto;
import com.ms.restaurant.exceptions.ServiceException;
import com.ms.restaurant.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${base.v1.endpoint}/user")
@Validated
public class UserRestController {

    Logger log = LoggerFactory.getLogger(UserRestController.class);

    protected final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping("/save")
//    public ResponseDto<?> addUser(@Valid @RequestBody UserRequestDto userRequestDto) {
//
//        AuthResponseDto userResponseDto = userService.adduser(userRequestDto);
//
//        log.info("User added Successfully");
//        return new SuccessResponseDto<>(userResponseDto, "User added Successfully and Otp Send successfully", HttpStatus.CREATED);
//    }

    @PutMapping("/update")
    public ResponseDto<?> updateUser(@Valid @RequestBody UserUpdateRequestDto userUpdateRequestDto) {

        UserResponseDto userResponseDto = userService.updateUser(userUpdateRequestDto);

        log.info("User updated Successfully");
        return new SuccessResponseDto<>(userResponseDto, "User updated Successfully", HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseDto<?> getUser(@PathVariable("id") Long id) {

        UserResponseDto userResponseDto = userService.getUser(id);

        log.info("User Fetch successfully");
        return new SuccessResponseDto<>(userResponseDto, "User Fetch successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto<?> deleteUser(@PathVariable("id") String id) {
        try {
            Long.valueOf(id);
        } catch (NumberFormatException ex) {
            throw new ServiceException("AS_16");
        }
        userService.deleteUser(Long.valueOf(id));

        log.info("Successfully delete");
        return new SuccessResponseDto<>("Successfully delete", Long.valueOf(id));
    }

    @GetMapping("/getByUsername/{id}")
//    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
    public ResponseDto<?> getByUsername(@PathVariable("id") String username) {

        UserResponseDto userResponseDto = userService.getByUsername(username);

        log.info("User Fetch successfully");
        return new SuccessResponseDto<>(userResponseDto, "User Fetch successfully", HttpStatus.OK);
    }

    @GetMapping("/getAll")
//    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
    public ResponseDto<?> getAllUser(@RequestParam("perPage") Integer perPage, @RequestParam("page") Integer page) {

        List<UserResponseDto> userResponseDtos = userService.getAllUser(perPage, page);

        log.info("Data fetch successfully");
        return new SuccessResponseDto<>(userResponseDtos, "Data fetch successfully",
                Long.valueOf(userResponseDtos.size()));
    }

    @PostMapping("/reSendOtp")
    public ResponseDto<?> reSendOtp(@RequestParam("username") String username) {
        userService.reSendOtp(username);

        log.info("Otp send successfully");
        return new SuccessResponseDto<>(null, "Otp Send successfully", HttpStatus.OK);
    }

//    @PostMapping("/updatePassword")
//    public ResponseDto<?> updatePassword(@Valid @RequestBody UpdatePasswordReqDto updatePasswordReqDto) {
//
//        userService.updatePassword(updatePasswordReqDto);
//
//        log.info("Password update successfully");
//        return new SuccessResponseDto<>(null, "Password update successfully", HttpStatus.OK);
//    }
//
//    @PostMapping("/accountVerification")
//    public ResponseDto<?> accountVerification(@RequestParam("username") String username, @RequestParam("otp") String otp) {
//
//        userService.accountVerification(username, otp);
//
//        log.info("Account verified successfully");
//        return new SuccessResponseDto<>(null, "Account verified successfully", HttpStatus.OK);
//    }
//
//    @PostMapping("/forgotPassword")
//    public ResponseDto<?> forgotPassword(@RequestParam("username") String username) {
//
//        userService.forgotPassword(username);
//
//        log.info("User found successfully, Otp send to email Id");
//        return new SuccessResponseDto<>(null, "User found successfully, Otp send to email Id", HttpStatus.OK);
//    }
//
//    @PostMapping("/resetPassword")
//    public ResponseDto<?> resetPassword(@Valid @RequestBody ResetPasswordReqDto resetPasswordReqDto) {
//
//        userService.resetPassword(resetPasswordReqDto);
//
//        log.info("User password reset successfully");
//        return new SuccessResponseDto<>(null, "User password reset successfully", HttpStatus.OK);
//    }

}
