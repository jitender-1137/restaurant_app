package com.ms.restaurant.dto.responseDto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponseDto {

    private Long id;
    private String name;
    private String username;
    private String email;
    private String age;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private RoleResponseDto role;
    //Admin domain
    private String firstName;
    private String lastName;
    private String mobileNumber;

}