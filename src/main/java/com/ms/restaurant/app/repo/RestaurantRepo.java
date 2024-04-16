package com.ms.restaurant.app.repo;

import com.ms.restaurant.domains.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {
}
