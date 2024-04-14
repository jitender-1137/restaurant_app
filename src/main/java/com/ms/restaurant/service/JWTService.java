package com.ms.restaurant.service;


import com.ms.restaurant.dto.responseDto.UserDto;

import java.util.Map;

public interface JWTService {

	Map<String, String> extractUsernameFromToken(String token);

	Boolean validateToken(String token, UserDto user);

	String generateToken(UserDto use);

	String generateRefreshToken(String token);
}
