package com.ms.restaurant.utils.validator;

import com.ms.restaurant.domains.Admin;
import com.ms.restaurant.domains.ERole;
import com.ms.restaurant.domains.Role;
import com.ms.restaurant.domains.User;
import com.ms.restaurant.dto.responseDto.AuthResponseDto;
import com.ms.restaurant.dto.responseDto.RoleResponseDto;
import com.ms.restaurant.dto.responseDto.UserDto;
import com.ms.restaurant.dto.responseDto.UserResponseDto;
import com.ms.restaurant.exceptions.ServiceException;
import com.ms.restaurant.jpaRepo.AdminRepository;
import com.ms.restaurant.jpaRepo.RoleRepository;
import com.ms.restaurant.jpaRepo.UserRepository;
import com.ms.restaurant.service.JWTService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class UserValidator {

    public static User getUserDetail(ModelMapper modelMapper) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            log.error("Unauthorized request");
            throw new ServiceException("AS_15", HttpStatus.UNAUTHORIZED);
        }

        User user = modelMapper.map(authentication.getPrincipal(), User.class);
        if (user == null || user.getId() == null || user.getId() <= 0) {
            log.error("User not found");
            throw new ServiceException("AS_01");
        }
        return user;
    }

    public static void checkIfUserVerifiedOrNot(Boolean forceValidation, AuthResponseDto authResponseDto) {
        if (!forceValidation) {

            log.error("Please verify your account, otp send to your email account");
            throw new ServiceException("AS_23", authResponseDto);
        }
    }

    public static void generateRole(String roleName, RoleRepository roleRepository) {
        if (!roleRepository.existsByNameAndEnabledTrue(roleName)) {
            Role role = new Role();
            role.setName(roleName);
            role.setCreatedBy("jitender221216@gmail.com");
            role.setUpdatedBy("jitender221216@gmail.com");
            role.setDescription("This role is only for " + roleName);
            roleRepository.save(role);
            log.info(roleName + " created successfully");
        }
    }

    public static void generateSuperUser(AdminRepository adminRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        if (!adminRepository.existsByUsernameAndEnabledTrue("jitender221216@gmail.com")) {
            Admin admin = new Admin();
            admin.setFirstName("Super");
            admin.setPassword("User");
            admin.setUsername("jitender221216@gmail.com");
            admin.setCreatedBy("jitender221216@gmail.com");
            admin.setUpdatedBy("jitender221216@gmail.com");
            admin.setPassword(passwordEncoder.encode("#Universal@app"));

            Role exsistRole = roleRepository.findByNameAndEnabledTrue(ERole.ROLE_SUPER_ADMIN.name()).orElseThrow(() -> new ServiceException("AS_11"));
            List<Role> roles = new LinkedList<>();
            roles.add(exsistRole);
            admin.setRole(roles);
            adminRepository.save(admin);
            log.info("Super Admin User created successfully");
        }
    }

    public static void validatePassword(String password, String confirmPassword) {
        if (password.trim().length() < 8) {
            log.error("Password length should be 8 or more");
            throw new ServiceException("AS_09");
        }
//		if (!CommonUtil.isValidPassword(userRequestDto.getPassword())) {
//			log.error("Password should be 8 or more characters with a mix of letters, numbers & symbols");
//			throw new ServiceException("AS_10");
//		}
        if (!password.equals(confirmPassword)) {
            log.error("Password and confirm Password must be same");
            throw new ServiceException("AS_08");
        }
    }

    public static AuthResponseDto generateAdminJwtToken(JWTService jwtService, ModelMapper modelMapper, Admin admin) {
        try {
            String jwtToken = jwtService.generateToken(modelMapper.map(admin, UserDto.class));
            UserResponseDto userResponseDto = modelMapper.map(admin, UserResponseDto.class);

            return new AuthResponseDto(jwtToken, userResponseDto);
        } catch (Exception e) {
            log.error("JWT Token creation failed : username :: {}", admin.getUsername());
            throw new ServiceException("AS_03");
        }
    }

    public static AuthResponseDto generateUserJwtToken(JWTService jwtService, ModelMapper modelMapper, User user) {
        try {
            String jwtToken = jwtService.generateToken(modelMapper.map(user, UserDto.class));
            UserResponseDto userResponseDto = modelMapper.map(user, UserResponseDto.class);

            return new AuthResponseDto(jwtToken, userResponseDto);
        } catch (Exception e) {
            log.error("JWT Token creation failed : username :: {}", user.getUsername());
            throw new ServiceException("AS_03");
        }
    }
}
