package com.ms.restaurant.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdminAuthRequestDto {

	@NotBlank
	private String username;

	@NotBlank
	private String password;

}
