package com.ms.restaurant.jpaRepo;

import com.ms.restaurant.domains.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}
