package com.ms.restaurant.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequestDto {

    private Integer roleId;

    @NotBlank
    private String name;

    @NotBlank
    @Size(max = 100)
    private String description;

}