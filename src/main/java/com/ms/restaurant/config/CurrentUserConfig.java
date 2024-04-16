package com.ms.restaurant.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@Configuration
public class CurrentUserConfig {

    @Bean
    public CurrentUser currentUser(HttpServletRequest request) {
        return new CurrentUser(request);
    }

    public static class CurrentUser {
        private final HttpServletRequest request;

        public CurrentUser(HttpServletRequest request) {
            this.request = request;
        }

        public String getUsername() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
                return authentication.getName();
            } else {
                // If no authentication or not authenticated, try to extract from HttpServletRequest
                // Modify this logic based on how your application handles authentication
                return request.getUserPrincipal() != null ? request.getUserPrincipal().getName() : null;
            }
        }
    }
}
