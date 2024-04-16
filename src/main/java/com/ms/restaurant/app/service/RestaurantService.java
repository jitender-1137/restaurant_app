package com.ms.restaurant.app.service;

import com.ms.restaurant.app.dto.RestaurantReqDto;
import com.ms.restaurant.app.dto.RestaurantResDto;

import java.util.List;

public interface RestaurantService {
    RestaurantResDto saveRestaurant(RestaurantReqDto restaurantRequestDto);

    void deleteRestaurant(Long id);

    RestaurantResDto getRestaurantById(Long id);

    List<RestaurantResDto> getAllRestaurants();

    RestaurantResDto updateRestaurant(RestaurantReqDto restaurantRequestDto);
}
