package com.ms.restaurant.app.repo;

import com.ms.restaurant.app.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Roles, Long> {
    Optional<Roles> findByName(String name);

    Optional<Roles> findByNameAndEnabledTrue(String name);
}
