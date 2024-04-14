package com.ms.restaurant.app.serviceImpl;

import com.ms.restaurant.app.dto.UserDetailsReqDto;
import com.ms.restaurant.app.dto.UserDetailsResDto;
import com.ms.restaurant.app.service.UserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetailsResDto getUser(Long id) {
        return null;
    }

    @Override
    public List<UserDetailsResDto> getAllUsers() {
        return null;
    }

    @Override
    public UserDetailsResDto saveUserDetails(UserDetailsReqDto userDetailsReqDto) {
        return null;
    }

    @Override
    public UserDetailsResDto updateUserProfile(UserDetailsReqDto userDetailsReqDto) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }
}
