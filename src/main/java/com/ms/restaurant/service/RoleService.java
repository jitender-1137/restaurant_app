package com.ms.restaurant.service;

import com.ms.restaurant.domains.Role;
import com.ms.restaurant.dto.requestDto.RoleRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.lang.NonNull;

import java.util.List;

public interface RoleService {

	String saveRole(@Valid RoleRequestDto roleRequestDto, @NonNull HttpServletRequest request);

	List<Role> getAllRoles();

	void deleteRole(Integer id);

	void assignRole(Integer valueOf, Long valueOf2);

}
