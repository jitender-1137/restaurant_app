package com.ms.restaurant.app.serviceImpl;

import com.ms.restaurant.app.dto.AdminLoginReqDto;
import com.ms.restaurant.app.dto.RoleResDto;
import com.ms.restaurant.app.dto.UserLoginReqDto;
import com.ms.restaurant.app.dto.UserLoginResDto;
import com.ms.restaurant.app.entity.Roles;
import com.ms.restaurant.app.entity.Users;
import com.ms.restaurant.app.repo.RoleRepo;
import com.ms.restaurant.app.repo.UserRepo;
import com.ms.restaurant.app.service.LoginService;
import com.ms.restaurant.domains.ERole;
import com.ms.restaurant.exceptions.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserLoginResDto adminLogin(AdminLoginReqDto adminLoginReq) {
        Users user = userRepo.findByUsernameAndEnabledTrue(adminLoginReq.getUsername().trim().toLowerCase())
                .orElseThrow(() -> new ServiceException("AS_01", HttpStatus.UNAUTHORIZED));

        if (!Objects.equals(user.getPassword(), adminLoginReq.getPassword())) {
            throw new ServiceException("AS_02");
        }
        List<RoleResDto> roles = user.getRoles().stream().map(role -> modelMapper.map(role, RoleResDto.class)).toList();

        UserLoginResDto userResDto = modelMapper.map(user, UserLoginResDto.class);
        userResDto.setRoles(roles);

        return userResDto;
    }

    @Override
    public UserLoginResDto findByUsername(String username) {
        Users user = new Users();
        username = username.trim().replaceAll("[^\\d]", "");
        Optional<Users> existingUser = userRepo.findByUsernameAndEnabledTrue(username);
        if (existingUser.isEmpty()) {
            user.setUsername(username);
            Roles roles = roleRepo.findByNameAndEnabledTrue(ERole.ROLE_USER.name()).orElseThrow(()-> new ServiceException("AS_03"));
            user.setRoles(List.of(roles));
            user.setPassword("123456");
            user = userRepo.save(user);
        } else {
            user = existingUser.get();
            user.setPassword("123456");
            userRepo.save(user);
        }
        //Send OTP (one time Password)


        List<RoleResDto> roles = user.getRoles().stream().map(role -> modelMapper.map(role, RoleResDto.class)).toList();
        UserLoginResDto userResDto = modelMapper.map(user, UserLoginResDto.class);
        userResDto.setRoles(roles);

        return userResDto;
    }

    @Override
    public UserLoginResDto userLogin(UserLoginReqDto userLoginReq) {
        Users user = userRepo.findByUsernameAndEnabledTrue(userLoginReq.getUsername().trim().replaceAll("[^\\d]", "").toLowerCase())
                .orElseThrow(() -> new ServiceException("AS_01", HttpStatus.UNAUTHORIZED));

        if (!Objects.equals(user.getPassword(), userLoginReq.getOtp())) {
            throw new ServiceException("AS_02");
        }
        List<RoleResDto> roles = user.getRoles().stream().map(role -> modelMapper.map(role, RoleResDto.class)).toList();

        UserLoginResDto userResDto = modelMapper.map(user, UserLoginResDto.class);
        userResDto.setRoles(roles);

        return userResDto;
    }

    @Override
    public UserLoginResDto sendOtp(String username) {
        Users user = new Users();
        Optional<Users> existingUser = userRepo.findByUsernameAndEnabledTrue(username.trim().toLowerCase());
        if (existingUser.isPresent()) {
            throw new ServiceException("AS_02");
        } else {
            user.setUsername(username);

            Roles roles = roleRepo.findByName(ERole.ROLE_ADMIN.name()).orElseThrow(()-> new ServiceException("AS_03"));
            user.setRoles(List.of(roles));
            user = userRepo.save(user);
        }
        //Send OTP (one time Password)
        user.setPassword("1234");
        userRepo.save(user);

        List<RoleResDto> roles = user.getRoles().stream().map(role -> modelMapper.map(role, RoleResDto.class)).toList();

        UserLoginResDto userResDto = modelMapper.map(user, UserLoginResDto.class);
        userResDto.setRoles(roles);

        return userResDto;
    }

    @Override
    public UserLoginResDto verifyOtp(String username, String otp) {
        Users user = userRepo.findByUsernameAndEnabledTrue(username.trim().toLowerCase())
                .orElseThrow(() -> new ServiceException("AS_01", HttpStatus.UNAUTHORIZED));

        if (!Objects.equals(user.getPassword(), otp)) {
            throw new ServiceException("AS_02");
        }
        List<RoleResDto> roles = user.getRoles().stream().map(role -> modelMapper.map(role, RoleResDto.class)).toList();
        UserLoginResDto userResDto = modelMapper.map(user, UserLoginResDto.class);
        userResDto.setRoles(roles);
        return userResDto;
    }

    @Override
    public UserLoginResDto signup(AdminLoginReqDto adminLoginReqDto) {
        Users user = userRepo.findByUsernameAndEnabledTrue(adminLoginReqDto.getUsername().trim().toLowerCase())
                .orElseThrow(() -> new ServiceException("AS_01", HttpStatus.UNAUTHORIZED));

        user.setPassword(adminLoginReqDto.getPassword().trim());

        user = userRepo.save(user);

        List<RoleResDto> roles = user.getRoles().stream().map(role -> modelMapper.map(role, RoleResDto.class)).toList();
        UserLoginResDto userResDto = modelMapper.map(user, UserLoginResDto.class);
        userResDto.setRoles(roles);
        return userResDto;
    }
}
