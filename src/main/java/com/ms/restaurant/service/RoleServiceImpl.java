package com.ms.restaurant.service;

import com.ms.restaurant.domains.ERole;
import com.ms.restaurant.domains.Role;
import com.ms.restaurant.domains.User;
import com.ms.restaurant.dto.requestDto.RoleRequestDto;
import com.ms.restaurant.exceptions.ServiceException;
import com.ms.restaurant.jpaRepo.RoleRepository;
import com.ms.restaurant.jpaRepo.UserRepository;
import com.ms.restaurant.utils.CommonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

	private final RoleRepository roleRepository;
	private final UserRepository userRepository;


	@Override
	public String saveRole(@Valid RoleRequestDto roleRequestDto, @NotNull HttpServletRequest request) {

		Role role = new Role();
		String username = (String) request.getSession().getAttribute("username");

		if (roleRepository.existsByNameAndEnabledTrue(roleRequestDto.getName().toUpperCase())) {
			log.error("Role already exists");
			throw new ServiceException("CS18");
		}

		Map<String, String> roleMap = new HashMap<>();
		for (ERole eRole : ERole.values()) {
			roleMap.put(eRole.name(), eRole.name());
		}

		if (!roleMap.containsKey(roleRequestDto.getName().toUpperCase())) {
			log.error("Unknown role");
			throw new ServiceException("CS20");
		}

		role = CommonUtil.convert(roleRequestDto, Role.class);
		role.setName(role.getName().toUpperCase());

		role.setCreatedBy(username);
		role.setUpdatedBy(username);
		role = roleRepository.save(role);
		if (role.getRoleId() == null) {
			log.error("Role creation failed");
			throw new ServiceException("CS13");
		}
		return role.getName();
	}

	@Override
	public List<Role> getAllRoles() {

		return roleRepository.findByEnabledTrue();
	}

	@Override
	public void deleteRole(Integer id) {

		Role role = roleRepository.findByRoleIdAndEnabledTrue(id).get();
		if (role.getRoleId() == null) {
			log.error("Role not found");
			throw new ServiceException("AS_11");
		}

		role.setEnabled(false);

		role = roleRepository.save(role);
		if (role.getRoleId() == null) {
			log.error("Role not found");
			throw new ServiceException("AS_11");
		}

	}

	@Override
	public void assignRole(Integer roleId, Long userId) {

		User user = userRepository.findByIdAndEnabledTrue(userId).get();
		if (user.getId() == null) {
			log.error("User not found");
			throw new ServiceException("AS_01");
		}

		Role role = roleRepository.findByRoleIdAndEnabledTrue(roleId).get();
		if (role.getRoleId() == null) {
			log.error("Role not found");
			throw new ServiceException("AS_11");
		}

		if (role.getName().equals("ROLE_SUPER_ADMIN")) {
			log.error("Cannot assign this role");
			throw new ServiceException("CS27");
		}

//		roles.add(role);
//
//		user.setRoles(roles);
		user.setRole(role);

		user = userRepository.save(user);
		if (user.getId() == null) {
			log.error("Failed to assign role");
			throw new ServiceException("CS28");
		}
	}

}
