package com.ms.restaurant.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResDto {

    private Long id;
    private String username;
    private List<RoleResDto> roles;
}
