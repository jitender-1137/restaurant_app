package com.ms.restaurant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        List<String> urls = new ArrayList<String>();
//        urls.add("http://127.0.0.1:5500".trim());
//        urls.add("http://localhost:3000/".trim());
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
////        config.setAllowedOrigins(urls);
//        config.addAllowedOrigin("*");
//        config.setAllowedMethods(List.of("*"));
//        config.setAllowedHeaders(List.of("*"));
//        config.setMaxAge(5000L);
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}