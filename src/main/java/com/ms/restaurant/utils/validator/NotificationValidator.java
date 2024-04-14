package com.ms.restaurant.utils.validator;

import com.ms.restaurant.domains.User;
import com.ms.restaurant.dto.requestDto.NotificationReqDto;
import com.ms.restaurant.utils.constants.NotificationConstants;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@NoArgsConstructor
public class NotificationValidator {
    
    public static NotificationReqDto setOtpVerificationNotificationData(User user, String otp) {
        NotificationReqDto notificationReqDto = new NotificationReqDto();
        notificationReqDto.setBrand(NotificationConstants.BRAND_NAME);
        notificationReqDto.setName(user.getName());
        notificationReqDto.setType(NotificationConstants.OTP_VERIFICATION_TYPE);
        notificationReqDto.setProviderType(NotificationConstants.OTP_MAIL_PROVIDER_TYPE);
        notificationReqDto.setToEmailId(user.getUsername());
        Map<String, String> valuedMap = new HashMap<>();
        valuedMap.put(NotificationConstants.OTP_MAP_KEY, otp);
        notificationReqDto.setDataMap(valuedMap);
        return notificationReqDto;
    }

    public static NotificationReqDto setAccountVerifiedNotificationData(User user) {
        NotificationReqDto notificationReqDto = new NotificationReqDto();
        notificationReqDto.setBrand(NotificationConstants.BRAND_NAME);
        notificationReqDto.setName(user.getName());
        notificationReqDto.setType(NotificationConstants.ACCOUNT_VERIFIED_TYPE);
        notificationReqDto.setProviderType(NotificationConstants.OTP_MAIL_PROVIDER_TYPE);
        notificationReqDto.setToEmailId(user.getUsername());
        return notificationReqDto;
    }

    public static NotificationReqDto setAccountDeletedNotificationData(User user) {
        NotificationReqDto notificationReqDto = new NotificationReqDto();
        notificationReqDto.setBrand(NotificationConstants.BRAND_NAME);
        notificationReqDto.setName(user.getName());
        notificationReqDto.setType(NotificationConstants.ACCOUNT_DELETED_TYPE);
        notificationReqDto.setProviderType(NotificationConstants.OTP_MAIL_PROVIDER_TYPE);
        notificationReqDto.setToEmailId(user.getUsername());
        return notificationReqDto;
    }

    public static NotificationReqDto setPasswordUpdatedNotificationData(User user) {
        NotificationReqDto notificationReqDto = new NotificationReqDto();
        notificationReqDto.setBrand(NotificationConstants.BRAND_NAME);
        notificationReqDto.setName(user.getName());
        notificationReqDto.setType(NotificationConstants.PASSWORD_UPDATED_TYPE);
        notificationReqDto.setProviderType(NotificationConstants.OTP_MAIL_PROVIDER_TYPE);
        notificationReqDto.setToEmailId(user.getUsername());
        return notificationReqDto;
    }
}
