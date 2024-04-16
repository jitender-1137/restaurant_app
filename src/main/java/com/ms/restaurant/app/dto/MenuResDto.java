package com.ms.restaurant.app.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MenuResDto {

    private Long id;
    private String name;
    private String price;
    private CategoryResDto catDto;
}
