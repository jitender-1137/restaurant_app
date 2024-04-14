package com.ms.restaurant.dto.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRequestDto {

	private Long id;
	@NotBlank
	@Size(min = 2)
	private String name;
	@NotBlank
	@Size(max = 50)
	@Email
	private String username;
	private String mobileNumber;
	@NotBlank
	@Size(max = 120)
	private String password;
	@NotBlank
	private String rePassword;

}