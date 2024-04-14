package com.ms.restaurant.jpaRepo;

import com.ms.restaurant.domains.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUsernameAndEnabledTrue(String username);

    boolean existsByUsernameAndEnabledTrue(String mail);
}