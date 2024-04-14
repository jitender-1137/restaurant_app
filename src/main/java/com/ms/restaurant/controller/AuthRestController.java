package com.ms.restaurant.controller;

import com.ms.restaurant.dto.requestDto.AdminAuthRequestDto;
import com.ms.restaurant.dto.requestDto.UserAuthRequestDto;
import com.ms.restaurant.dto.responseDto.AuthResponseDto;
import com.ms.restaurant.dto.responseDto.ResponseDto;
import com.ms.restaurant.dto.responseDto.SuccessResponseDto;
import com.ms.restaurant.jpaRepo.MenuRepository;
import com.ms.restaurant.jpaRepo.RestaurantRepository;
import com.ms.restaurant.service.AuthService;
import com.ms.restaurant.service.JWTService;
import com.ms.restaurant.utils.constants.SecurityConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("${base.v1.endpoint}")
@Validated
@RequiredArgsConstructor
@Slf4j
public class AuthRestController {

    private final AuthService authService;
    private final JWTService jwtService;
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    @PostMapping("/login/admin")
    public ResponseDto<?> adminLogin(@Valid @RequestBody AdminAuthRequestDto adminAuthRequestDto) {
        AuthResponseDto authResponseDto = authService.adminLogin(adminAuthRequestDto);
        log.info("Successfully login");
        return new SuccessResponseDto<>(authResponseDto, "Successfully Login", HttpStatus.OK);

    }

    @PostMapping("/login/user")
    public ResponseDto<?> userLogin(@Valid @RequestBody UserAuthRequestDto userAuthRequestDto) {
        AuthResponseDto authResponseDto = authService.userLogin(userAuthRequestDto);
        log.info("Successfully login");
        return new SuccessResponseDto<>(authResponseDto, "Successfully Login", HttpStatus.OK);

    }

    @GetMapping("/login/addSuperUser")
    public String addSuperUser() {
        authService.addSuperUser();
        return "Super User added successfully";
    }
    @GetMapping("/login/a/{id}")
    public ResponseDto<?> a(@PathVariable Long id) {
        System.out.println(restaurantRepository.findById(id));
        return new SuccessResponseDto<>(restaurantRepository.findById(id), "Restaurant", HttpStatus.OK);
    }
    @GetMapping("/login/b/{id}")
    public ResponseDto<?> b(@PathVariable Long id) {
        return new SuccessResponseDto<>(menuRepository.findById(id), "Menu", HttpStatus.OK);
    }

    @PostMapping("/refreshToken")
    public ResponseDto<?> refreshToken(@NotNull HttpServletRequest request) {
        String refreshToken = jwtService.generateRefreshToken(request.getHeader(SecurityConstants.TOKEN_HEADER));
        Map<String, String> refreshTokenMap = new HashMap<>();
        refreshTokenMap.put("refreshToken", refreshToken);
        return new SuccessResponseDto<>(refreshTokenMap, "Refresh Token Generated", HttpStatus.OK);
    }
}
