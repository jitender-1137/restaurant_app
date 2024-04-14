package com.ms.restaurant.utils.validator;

import com.ms.restaurant.exceptions.ServiceException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@NoArgsConstructor
public class OtpValidator {


    public static void isOtpExpired(LocalDateTime updatedAt) {
        if (updatedAt.plusMinutes(5).isBefore(LocalDateTime.now())) {
            log.error("OTP expired");
            throw new ServiceException("AS_18");
        }
    }

    public static void isOtpMismatch(String existOtp, String userInput) {
        if (!existOtp.equals(userInput)) {
            log.error("OTP mismatched");
            throw new ServiceException("AS_19");
        }
    }

    public static void checkOtpLastSend(LocalDateTime updatedAt) {
        if (updatedAt.plusMinutes(1).isAfter(LocalDateTime.now())) {
            log.error("Resend otp after 1 minute");
            throw new ServiceException("AS_24");
        }
    }

    public static void checkOtpSendCount(int sendCount) {
        if (sendCount >= 5) {
            log.error("OTP send maximum time");
            throw new ServiceException("AS_25");
        }
    }
}
