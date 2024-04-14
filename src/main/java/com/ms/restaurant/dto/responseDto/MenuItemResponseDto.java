package com.ms.restaurant.dto.responseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuItemResponseDto {
    private Long id;
    private String name;
    private String description;
    private double price;
    private boolean available;
    private Long categoryId;

}
