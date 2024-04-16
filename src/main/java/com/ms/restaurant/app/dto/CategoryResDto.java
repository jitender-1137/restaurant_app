package com.ms.restaurant.app.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CategoryResDto {

    private Long id;
    private String name;

    private List<MenuResDto> menu;
}
