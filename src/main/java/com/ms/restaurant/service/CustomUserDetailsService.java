package com.ms.restaurant.service;

import com.ms.restaurant.domains.Admin;
import com.ms.restaurant.domains.ERole;
import com.ms.restaurant.domains.User;
import com.ms.restaurant.dto.responseDto.UserDto;
import com.ms.restaurant.jpaRepo.AdminRepository;
import com.ms.restaurant.jpaRepo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;
    protected final UserRepository userRepository;
    private final ModelMapper modelMapper;

    private static Boolean isRoleUser = false;

    public UserDto loadUserByUsernameAndRole(String username, String role) {
        isRoleUser = Objects.equals(role, ERole.ROLE_USER.name());
        return loadUserByUsername(username);
    }

    @Override
    public UserDto loadUserByUsername(String username) throws UsernameNotFoundException {
        if (isRoleUser) {
            User user = userRepository.findByUsernameAndEnabledTrue(username)
                    .orElseThrow(() -> new UsernameNotFoundException("No user found"));
            return modelMapper.map(user, UserDto.class);

        } else {
            Admin admin = adminRepository.findByUsernameAndEnabledTrue(username)
                    .orElseThrow(() -> new UsernameNotFoundException("No admin found"));
            return modelMapper.map(admin, UserDto.class);
        }
    }
}
