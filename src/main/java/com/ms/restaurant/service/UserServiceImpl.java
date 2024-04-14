package com.ms.restaurant.service;

import com.ms.restaurant.domains.Otp;
import com.ms.restaurant.domains.User;
import com.ms.restaurant.dto.requestDto.NotificationReqDto;
import com.ms.restaurant.dto.requestDto.UserUpdateRequestDto;
import com.ms.restaurant.dto.responseDto.UserResponseDto;
import com.ms.restaurant.exceptions.ServiceException;
import com.ms.restaurant.jpaRepo.OtpRepository;
import com.ms.restaurant.jpaRepo.RoleRepository;
import com.ms.restaurant.jpaRepo.UserRepository;
import com.ms.restaurant.utils.CommonUtil;
import com.ms.restaurant.utils.validator.NotificationValidator;
import com.ms.restaurant.utils.validator.OtpValidator;
import com.ms.restaurant.utils.validator.UserValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final OtpRepository otpRepository;

//    @Override
//    @Transactional
//    public AuthResponseDto adduser(@Valid UserRequestDto userRequestDto) {
//        if (userRepository.existsByUsernameAndEnabledTrue(userRequestDto.getUsername())) {
//            log.error("Email Already Taken");
//            throw new ServiceException("AS_04");
//        }
//        UserValidator.validatePassword(userRequestDto.getPassword(), userRequestDto.getRePassword());
//        User user = setUserData(userRequestDto);
//
//        Role role = roleRepository.findByNameAndEnabledTrue(ERole.ROLE_USER.name())
//                .orElseThrow(() -> new ServiceException("AS_11"));
//
//        user.setRole(role);
//
//        String newOtp = CommonUtil.generateNumberOtp();
//        NotificationReqDto notificationReqDto = NotificationValidator.setOtpVerificationNotificationData(user, newOtp);
//
//        NotificationRestClientConfig.sendOtp(restClient, notificationReqDto);
//
//        if (userRepository.save(user).getId() == null) {
//            log.error("User Creation failed");
//            throw new ServiceException("AS_12");
//        }
//        Otp otp = new Otp(newOtp, user.getId(), user.getUsername(), user.getUsername());
//
//        if (otpRepository.save(otp).getId() == null) {
//            log.error("Otp failed to save");
//            throw new ServiceException("AS_13");
//        }
//        log.info("Otp : {} send successfully to {}.", newOtp, user.getUsername());
//        return UserValidator.generateJwtToken(jwtService, modelMapper, user);
//    }
//
//    private User setUserData(UserRequestDto userRequestDto) {
//        User user = CommonUtil.convert(userRequestDto, User.class);
//        user.setUsername(user.getUsername().trim().toLowerCase());
//        user.setCreatedBy(user.getUsername());
//        user.setUpdatedBy(user.getUsername());
//        user.setPassword(passwordEncoder.encode(user.getPassword().trim()));
//        return user;
//    }

    @Override
    public UserResponseDto updateUser(@Valid UserUpdateRequestDto userUpdateRequestDto) {
        User existingUser = userRepository.findByIdAndEnabledTrue(Long.valueOf(userUpdateRequestDto.getId()))
                .orElseThrow(() -> new ServiceException("AS_01"));

        existingUser.setName(userUpdateRequestDto.getName());
        existingUser.setAge(userUpdateRequestDto.getAge());
        existingUser.setEmail(userUpdateRequestDto.getEmail());
        existingUser.setUpdatedBy(existingUser.getUsername());

        if (userRepository.save(existingUser).getId() == null) {
            log.error("User update failed");
            throw new ServiceException("AS_14");
        }
        return modelMapper.map(existingUser, UserResponseDto.class);
    }

    @Override
    public UserResponseDto getUser(Long id) {
        User user = userRepository.findByIdAndEnabledTrue(id)
                .orElseThrow(() -> new ServiceException("AS_01"));

        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    public List<UserResponseDto> getAllUser(Integer perPage, Integer page) {
        UserValidator.getUserDetail(modelMapper);
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        setPageable(perPage, page);

        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            log.info("No user present in the system");
            return userResponseDtos;
        }
        for (User user : users) {
            userResponseDtos.add(modelMapper.map(user, UserResponseDto.class));
        }
        return userResponseDtos;
    }

    @Override
    public void deleteUser(Long id) {
        User user = UserValidator.getUserDetail(modelMapper);
        if (!Objects.equals(id, user.getId())) {
            log.error("Incorrect user ID");
            throw new ServiceException("AS_16");
        }
        user.setEnabled(false);
        userRepository.save(user);

        NotificationReqDto notificationReqDto = NotificationValidator.setAccountDeletedNotificationData(user);
//        NotificationRestClientConfig.sendOtp(restClient, notificationReqDto);
    }

    @Override
    public UserResponseDto getByUsername(String username) {
        User user = userRepository.findByUsernameAndEnabledTrue(username)
                .orElseThrow(() -> new ServiceException("AS_01"));

        return modelMapper.map(user, UserResponseDto.class);
    }

//    @Override
//    @Transactional
//    public void accountVerification(String username, String otp) {
//        User user = userRepository.findByUsernameAndEnabledTrue(username)
//                .orElseThrow(() -> new ServiceException("AS_01"));
//        if (!user.getUsername().equals(username)) {
//            log.error("Incorrect username");
//            throw new ServiceException("AS_28");
//        }
//        if (user.getForceValidation()) {
//            throw new ServiceException("AS_22");
//        }
//        Otp existingOtp = otpRepository.findByUserIdAndEnabledTrue(user.getId())
//                .orElseThrow(() -> new ServiceException("AS_17"));
//
//        OtpValidator.isOtpExpired(existingOtp.getUpdatedAt());
//        OtpValidator.isOtpMismatch(existingOtp.getOtp(), otp);
//
//        existingOtp.setEnabled(false);
//        if (otpRepository.save(existingOtp).getId() == null) {
//            throw new ServiceException("AS_20");
//        }
//        user.setForceValidation(true);
//        userRepository.save(user);
//
//        NotificationReqDto notificationReqDto = NotificationValidator.setAccountVerifiedNotificationData(user);
//        NotificationRestClientConfig.sendOtp(restClient, notificationReqDto);
//    }

    @Override
    public void reSendOtp(String username) {
        User user = userRepository.findByUsernameAndEnabledTrue(username)
                .orElseThrow(() -> new ServiceException("AS_01"));
        if (!user.getUsername().equals(username)) {
            log.error("Incorrect username");
            throw new ServiceException("AS_28");
        }
        Otp existingOtp = otpRepository.findByUserIdAndEnabledTrue(user.getId())
                .orElseThrow(() -> new ServiceException("AS_17"));

        OtpValidator.checkOtpLastSend(existingOtp.getUpdatedAt());
        OtpValidator.checkOtpSendCount(existingOtp.getSendCount());

        String newOtp = CommonUtil.generateNumberOtp();
        NotificationReqDto notificationReqDto = NotificationValidator.setOtpVerificationNotificationData(user, newOtp);

//        NotificationRestClientConfig.sendOtp(restClient, notificationReqDto);

        existingOtp.setOtp(newOtp);
        existingOtp.setSendCount(existingOtp.getSendCount() + 1);
        if (otpRepository.save(existingOtp).getId() == null) {
            throw new ServiceException("AS_20");
        }
    }

//    @Override
//    @Transactional
//    public void updatePassword(@Valid UpdatePasswordReqDto updatePasswordReqDto) {
//        User user = UserValidator.getUserDetail(modelMapper);
//
//        UserValidator.validatePassword(updatePasswordReqDto.getPassword(), updatePasswordReqDto.getRePassword());
//        user.setPassword(passwordEncoder.encode(updatePasswordReqDto.getPassword()));
//
//        if (userRepository.save(user).getId() == null) {
//            log.error("Password update failed");
//            throw new ServiceException("AS_26");
//        }
//
//        NotificationReqDto notificationReqDto = NotificationValidator.setPasswordUpdatedNotificationData(user);
//        NotificationRestClientConfig.sendOtp(restClient, notificationReqDto);
//    }
//
//    @Override
//    @Transactional
//    public void forgotPassword(String username) {
//        User user = userRepository.findByUsernameAndEnabledTrue(username)
//                .orElseThrow(() -> new ServiceException("AS_01"));
//
//        String newOtp = CommonUtil.generateNumberOtp();
//        Otp otp;
//        try {
//            otp = otpRepository.findByUserIdAndEnabledTrue(user.getId()).get();
//            if (otp.getId() != null) {
//                otp.setOtp(newOtp);
//            }
//        } catch (Exception e) {
//            otp = new Otp(newOtp, user.getId(), user.getUsername(), user.getUsername());
//        }
//        NotificationReqDto notificationReqDto = NotificationValidator.setOtpVerificationNotificationData(user, newOtp);
//
//        NotificationRestClientConfig.sendOtp(restClient, notificationReqDto);
//
//        if (otpRepository.save(otp).getId() == null) {
//            log.error("Otp failed to save");
//            throw new ServiceException("AS_13");
//        }
//    }
//
//    @Override
//    @Transactional
//    public void resetPassword(ResetPasswordReqDto resetPasswordReqDto) {
//        User user = userRepository.findByUsernameAndEnabledTrue(resetPasswordReqDto.getUsername())
//                .orElseThrow(() -> new ServiceException("AS_01"));
//
//        Otp existingOtp = otpRepository.findByUserIdAndEnabledTrue(user.getId())
//                .orElseThrow(() -> new ServiceException("AS_17"));
//
//        OtpValidator.isOtpExpired(existingOtp.getUpdatedAt());
//        OtpValidator.isOtpMismatch(existingOtp.getOtp(), resetPasswordReqDto.getOtp());
//
//        existingOtp.setEnabled(false);
//        if (otpRepository.save(existingOtp).getId() == null) {
//            throw new ServiceException("AS_20");
//        }
//        user.setForceValidation(true);
//        userRepository.save(user);
//
//
//        user.setPassword(passwordEncoder.encode(resetPasswordReqDto.getPassword()));
//
//        if (userRepository.save(user).getId() == null) {
//            log.error("Password reset failed");
//            throw new ServiceException("AS_27");
//        }
//
//        NotificationReqDto notificationReqDto = NotificationValidator.setPasswordUpdatedNotificationData(user);
//        NotificationRestClientConfig.sendOtp(restClient, notificationReqDto);
//    }


    private Pageable setPageable(Integer perPage, Integer page) {
        if (perPage == null || perPage == 0) {
            perPage = 10;
        }
        if (page == null || page == 0) {
            page = 1;
        }
        Pageable pageable = PageRequest.of(page - 1, perPage);
        return pageable;
    }
}
