package com.ms.restaurant.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddressRequestDto {

    private Long id;

    @NotBlank
    private String lineOne;

    @NotBlank
    private String lineTwo;

    @NotBlank
    private String pincode;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String country;

}