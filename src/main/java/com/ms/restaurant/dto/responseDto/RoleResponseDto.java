package com.ms.restaurant.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class RoleResponseDto {

    private Integer roleId;
    private String name;
    private String description;
}
