package com.ms.restaurant.service;

import com.ms.restaurant.domains.Admin;
import com.ms.restaurant.jpaRepo.AdminRepository;
import com.ms.restaurant.domains.ERole;
import com.ms.restaurant.domains.User;
import com.ms.restaurant.dto.requestDto.AdminAuthRequestDto;
import com.ms.restaurant.dto.requestDto.UserAuthRequestDto;
import com.ms.restaurant.dto.responseDto.AuthResponseDto;
import com.ms.restaurant.exceptions.ServiceException;
import com.ms.restaurant.jpaRepo.RoleRepository;
import com.ms.restaurant.jpaRepo.UserRepository;
import com.ms.restaurant.utils.validator.UserValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JWTService jwtService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponseDto adminLogin(@Valid AdminAuthRequestDto adminAuthRequestDto) {
        Admin admin = adminRepository.findByUsernameAndEnabledTrue(adminAuthRequestDto.getUsername().trim().toLowerCase()).orElseThrow(() -> new ServiceException("AS_01", HttpStatus.UNAUTHORIZED));

        if (!BCrypt.checkpw(adminAuthRequestDto.getPassword().trim(), admin.getPassword())) {
            log.error("Wrong credentials : username :: {}", admin.getUsername());
            throw new ServiceException("AS_02", HttpStatus.UNAUTHORIZED);
        }
        AuthResponseDto authResponseDto = UserValidator.generateAdminJwtToken(jwtService, modelMapper, admin);
        UserValidator.checkIfUserVerifiedOrNot(admin.getForceValidation(), authResponseDto);
        return authResponseDto;
    }

    @Override
    public AuthResponseDto userLogin(UserAuthRequestDto userAuthRequestDto) {
        Optional<User> existingUser = userRepository.findByUsernameAndEnabledTrue(userAuthRequestDto.getUsername().trim().toLowerCase());
        String mobileNumber = userAuthRequestDto.getUsername();
        if (existingUser.isEmpty()) {
            User user = new User();
            user.setUsername(mobileNumber);
            user.setRole(roleRepository.findByNameAndEnabledTrue(ERole.ROLE_SUPER_ADMIN.name()).orElseThrow(() -> new ServiceException("AS_11")));
            user = userRepository.save(user);
            if (user.getId() != null) {
                return UserValidator.generateUserJwtToken(jwtService, modelMapper, user);
            } else throw new ServiceException("AS_12");
        } else {
            return UserValidator.generateUserJwtToken(jwtService, modelMapper, existingUser.get());
        }
    }

    @Override
    public void addSuperUser() {
        for (ERole eRole : ERole.values()) {
            try {
                UserValidator.generateRole(eRole.name(), roleRepository);
            } catch (Exception e) {
                UserValidator.generateRole(eRole.name(), roleRepository);
            }
        }
        try {
            UserValidator.generateSuperUser(adminRepository, roleRepository, passwordEncoder);
        } catch (Exception e) {
            UserValidator.generateSuperUser(adminRepository, roleRepository, passwordEncoder);
        }
    }

}
