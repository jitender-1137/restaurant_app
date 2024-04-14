package com.ms.restaurant.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.restaurant.dto.responseDto.ErrorResponseDto;
import com.ms.restaurant.dto.responseDto.UserDto;
import com.ms.restaurant.service.CustomUserDetailsService;
import com.ms.restaurant.service.JWTService;
import com.ms.restaurant.utils.constants.SecurityConstants;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
@Slf4j
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;
    private final JWTService jwtService;
    private final CustomUserDetailsService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader(SecurityConstants.TOKEN_HEADER);
        String jwt;
        String subject;
        String role;
        UserDto user;
        if (authHeader == null || !authHeader.startsWith(SecurityConstants.TOKEN_TYPE)) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        try {
            Map<String, String> claimsMap = jwtService.extractUsernameFromToken(jwt);
            role = claimsMap.entrySet().stream().findFirst().get().getKey();
            subject = claimsMap.get(role);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            String code = String.valueOf(UNAUTHORIZED.value());
            if (ex instanceof ExpiredJwtException)
                code = "JE_01";
            ErrorResponseDto<?> errorResponseDto = new ErrorResponseDto<>(code, ex.getMessage());
            response.setStatus(UNAUTHORIZED.value());
            response.setContentType(APPLICATION_JSON_VALUE);
            objectMapper.writeValue(response.getOutputStream(), errorResponseDto);
            return;
        }

        if (subject != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                user = authService.loadUserByUsernameAndRole(subject, role);
            } catch (Exception ex) {
                log.error(ex.getMessage());
                String code = String.valueOf(UNAUTHORIZED.value());
                ErrorResponseDto<?> errorResponseDto = new ErrorResponseDto<>(code, ex.getMessage());
                response.setStatus(UNAUTHORIZED.value());
                response.setContentType(APPLICATION_JSON_VALUE);
                objectMapper.writeValue(response.getOutputStream(), errorResponseDto);
                return;
            }
            if (jwtService.validateToken(jwt, user)) {
                var authToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}