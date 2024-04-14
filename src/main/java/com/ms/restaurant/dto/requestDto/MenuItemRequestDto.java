package com.ms.restaurant.dto.requestDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuItemRequestDto {
    private String name;
    private String description;
    private double price;
    private boolean available;
    private Long categoryId;

}
