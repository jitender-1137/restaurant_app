package com.ms.restaurant.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsReqDto {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String age;
    private String gender;
    private String profileImageUrl;
}
