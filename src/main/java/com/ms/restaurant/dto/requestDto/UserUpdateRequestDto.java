package com.ms.restaurant.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequestDto {

    @NotEmpty
    private String id;

    @NotBlank
    @Size(min = 2)
    private String name;

    private String age;
    private String email;

}