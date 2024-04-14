package com.ms.restaurant.jpaRepo;

import com.ms.restaurant.domains.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Optional<Role> findByNameAndEnabledTrue(String name);

	boolean existsByNameAndEnabledTrue(String name);

	Optional<Role> findByRoleIdAndEnabledTrue(Integer id);

	List<Role> findByEnabledTrue();

}
