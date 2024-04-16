package com.ms.restaurant.app.serviceImpl;

import com.ms.restaurant.app.dto.RestaurantReqDto;
import com.ms.restaurant.app.dto.RestaurantResDto;
import com.ms.restaurant.app.entity.Restaurants;
import com.ms.restaurant.app.entity.Users;
import com.ms.restaurant.app.repo.RestaurantRepo;
import com.ms.restaurant.app.repo.UserRepo;
import com.ms.restaurant.app.service.RestaurantService;
import com.ms.restaurant.config.CurrentUserConfig;
import com.ms.restaurant.exceptions.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    protected  final RestaurantRepo restaurantRepo;
    protected final UserRepo userRepo;
    protected final ModelMapper modelMapper;
    protected final CurrentUserConfig.CurrentUser currentUserConfig;

    @Override
    public RestaurantResDto saveRestaurant(RestaurantReqDto restaurantRequestDto) {
        Users user = getCurrentUser();
        Restaurants restaurants = new Restaurants();
        restaurants.setName(restaurantRequestDto.getName());
        return null;
    }

    @Override
    public void deleteRestaurant(Long id) {

    }

    @Override
    public RestaurantResDto getRestaurantById(Long id) {
        return null;
    }

    @Override
    public List<RestaurantResDto> getAllRestaurants() {
        return null;
    }

    @Override
    public RestaurantResDto updateRestaurant(RestaurantReqDto restaurantRequestDto) {
        return null;
    }

    private Users getCurrentUser() {
        String username = currentUserConfig.getUsername();
        return userRepo.findByUsernameAndEnabledTrue(username)
               .orElseThrow(() -> new ServiceException("AS_01", HttpStatus.UNAUTHORIZED));
    }
}
