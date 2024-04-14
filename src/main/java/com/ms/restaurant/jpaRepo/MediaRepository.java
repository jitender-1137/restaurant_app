package com.ms.restaurant.jpaRepo;

import com.ms.restaurant.domains.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long> {
}