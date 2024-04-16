package com.ms.restaurant.app.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantResDto {

    private Long id;

    private String name;

    private List<CategoryResDto> categories;

//    @JsonView(Views.UserView.class) // Include in UserView
//    private Long id;
//
//    @JsonView(Views.UserView.class) // Include in UserView
//    private String resName;
//
//    @JsonView(Views.RestaurantView.class) // Include in RestaurantView
//    private List<CatDto> catDto;

//    public ResDto(Long id, String name) {
//        this.id = id;
//        this.resName = name;
//    }
}
