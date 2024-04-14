package com.ms.restaurant.filter;

import com.ms.restaurant.dto.responseDto.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthContext {

    public UserDto getAuthContext() {
        // Retrieve the authentication object from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        // Check if the authentication object is not null and if it is authenticated
        if (authentication != null && authentication.isAuthenticated()) {
            // Get the principal object
            Object principal = authentication.getPrincipal();
            
            // You can then extract information from the principal object as needed
            // For example, if the principal is a UserDetails object:
            if (principal instanceof UserDto) {
                return (UserDto) principal;
                // Use the username or other details as needed
            }
        } else {
            return new UserDto();
            // No authenticated user
        }
        return null;
    }
}
