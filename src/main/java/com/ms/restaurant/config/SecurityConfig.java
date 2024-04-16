package com.ms.restaurant.config;

import com.ms.restaurant.filter.CorsLoggingFilter;
import com.ms.restaurant.filter.JWTAuthenticationFilter;
import com.ms.restaurant.handler.AccessDeniedHandlerJwt;
import com.ms.restaurant.handler.JWTAuthenticationEntryPoint;
import com.ms.restaurant.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Value("${base.v1.endpoint}")
    protected String baseApiPath;

    protected final CustomUserDetailsService authService;
    protected final CorsLoggingFilter corsLoggingFilter;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(authService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    private String[] unSecured() {
//        return new String[]{baseApiPath + "/login/**", "/swagger-ui/**", "/resources/**", "/v3/api-docs/**", "/**",
//                baseApiPath + "/user/save", baseApiPath + "/user/forgotPassword/**", baseApiPath + "/user/resetPassword", baseApiPath + "/user/resetPassword11",
//                baseApiPath + "/user/reSendOtp/**", baseApiPath + "/user/accountVerification/**", "/actuator/**"};
        return new String[]{"/**"};
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, AccessDeniedHandlerJwt accessDeniedHandlerJwt,
                                            JWTAuthenticationEntryPoint jwtAuthanticationEntryPoint,
                                            JWTAuthenticationFilter authenticationFilter) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authReq -> authReq.requestMatchers(unSecured()).permitAll())
                .authorizeHttpRequests(authReq -> authReq.anyRequest().authenticated())
                .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthanticationEntryPoint))
                .exceptionHandling(ex -> ex.accessDeniedHandler(accessDeniedHandlerJwt))
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public FilterRegistrationBean<CorsLoggingFilter> loggingFilterRegistration() {
        FilterRegistrationBean<CorsLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(corsLoggingFilter);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
