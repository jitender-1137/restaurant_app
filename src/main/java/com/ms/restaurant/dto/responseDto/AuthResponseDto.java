package com.ms.restaurant.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponseDto {

	private String token;

	private UserResponseDto userResponseDto;

}