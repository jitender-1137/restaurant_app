package com.ms.restaurant.app.controller;

import com.ms.restaurant.app.dto.UserDetailsReqDto;
import com.ms.restaurant.app.dto.UserDetailsResDto;
import com.ms.restaurant.app.service.UserDetailsService;
import com.ms.restaurant.dto.responseDto.ResponseDto;
import com.ms.restaurant.dto.responseDto.SuccessResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserDetailsController {

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/get")
    public ResponseDto<?> getUser(@RequestParam("id") Long id) {
        UserDetailsResDto userDetailsResDto = userDetailsService.getUser(id);

        return new SuccessResponseDto<>(userDetailsResDto, "User Fetch successfully", HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseDto<?> getAllUsers() {
        List<UserDetailsResDto> userDetailsResDtos = userDetailsService.getAllUsers();

        return new SuccessResponseDto<>(userDetailsResDtos, "Users Fetch successfully", HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseDto<?> saveUser(@RequestBody UserDetailsReqDto userDetailsReqDto) {
        UserDetailsResDto userDetailsResDto = userDetailsService.saveUserDetails(userDetailsReqDto);

        return new SuccessResponseDto<>(userDetailsResDto, "User Save successfully", HttpStatus.CREATED);
    }
    
    @PutMapping("/update")
    public ResponseDto<?> updateUser(@RequestBody UserDetailsReqDto userDetailsReqDto) {
        UserDetailsResDto userDetailsResDto = userDetailsService.updateUserProfile(userDetailsReqDto);

        return new SuccessResponseDto<>(userDetailsResDto, "User Update successfully", HttpStatus.OK);
    }
    
    @DeleteMapping("/delete")
    public ResponseDto<?> deleteUser(@RequestParam("id") Long id) {
        userDetailsService.deleteUser(id);

        return new SuccessResponseDto<>(null, "User Delete successfully", HttpStatus.OK);
    }
}
