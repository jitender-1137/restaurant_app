package com.ms.restaurant.jpaRepo;

import com.ms.restaurant.domains.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, Long>  {

    Optional<Otp> findByUserIdAndEnabledTrue(Long userId);
}
