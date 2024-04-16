package com.ms.restaurant.app.controller;

import com.ms.restaurant.app.dto.RestaurantReqDto;
import com.ms.restaurant.app.dto.RestaurantResDto;
import com.ms.restaurant.app.service.RestaurantService;
import com.ms.restaurant.dto.responseDto.ResponseDto;
import com.ms.restaurant.dto.responseDto.SuccessResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/res")
@Slf4j
@Validated
@RequiredArgsConstructor
public class RestaurantController {

    protected final RestaurantService restaurantService;

    @PostMapping("/save")
    public ResponseDto<?> saveRestaurant(@Valid @RequestBody RestaurantReqDto restaurantRequestDto) {
        RestaurantResDto restaurantResDto = restaurantService.saveRestaurant(restaurantRequestDto);
        return new SuccessResponseDto<>(restaurantResDto, "Restaurant Saved", HttpStatus.CREATED);
    }

    @PutMapping("/save")
    public ResponseDto<?> updateRestaurant(@Valid @RequestBody RestaurantReqDto restaurantRequestDto) {
        RestaurantResDto restaurantResDto = restaurantService.updateRestaurant(restaurantRequestDto);
        return new SuccessResponseDto<>(restaurantResDto, "Restaurant updated", HttpStatus.OK);
    }


    @GetMapping("/getAll")
    public ResponseDto<?> getAllRestaurants() {
        List<RestaurantResDto> restaurantResDtos =restaurantService.getAllRestaurants();
        return new SuccessResponseDto<>(restaurantResDtos, "Restaurants Fetch successfully", HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseDto<?> getRestaurantById(@PathVariable Long id) {
        RestaurantResDto restaurantResDto = restaurantService.getRestaurantById(id);
        return new SuccessResponseDto<>(restaurantResDto, "Restaurant Fetch successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto<?> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return new SuccessResponseDto<>(null, "Restaurant Deleted", HttpStatus.OK);
    }
}
