package com.ms.restaurant.jpaRepo;

import com.ms.restaurant.domains.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> getByUserId(Long user_id);

    Optional<Category> findByIdAndUserIdAndEnabledTrue(Long categoryId, Long id);

    boolean existsByNameAndUserIdAndEnabledTrue(String name, Long id);

    boolean existsByIdAndUserIdAndEnabledTrue(Long categoryId, Long id);
}
