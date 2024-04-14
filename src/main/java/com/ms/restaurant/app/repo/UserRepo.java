package com.ms.restaurant.app.repo;


import com.ms.restaurant.app.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users, Long> {
    Optional<Users> findByUsernameAndEnabledTrue(String username);
}
