package com.ms.restaurant.service;

import com.ms.restaurant.dto.responseDto.UserDto;
import com.ms.restaurant.utils.constants.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@Slf4j
@RequiredArgsConstructor
public class JWTServiceImpl implements JWTService {

    @Value("${spring.jwt.secret}")
    protected String JWT_SECRET;

    @Value("${spring.jwt.jwtExpirationInMs}")
    protected int JWT_EXPIRATION_TIME_IN_MILLISECONDS;

    @Override
    public String generateToken(UserDto user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(SecurityConstants.ROLES_KEY, user.getAuthorities());
        return tokenCreator(claims, user.getUsername());
    }

    @Override
    public String generateRefreshToken(String token) {
        Claims claims = extractAllClaims(token.substring(7));
        return tokenCreator(claims, claims.getSubject());
    }

    public String tokenCreator(Map<String, Object> claims, String username) {
        return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_TIME_IN_MILLISECONDS))
                .signWith(getSignedKey(), SignatureAlgorithm.HS256).compact();
    }

    @Override
    public Map<String, String> extractUsernameFromToken(String theToken) {
        isTokenExpired(theToken);
        Claims claims = extractAllClaims(theToken);
        List<Map<String, String>> roles = (List<Map<String, String>>) claims.get(SecurityConstants.ROLES_KEY);
        if (!roles.isEmpty()) {
            Map<String, String> role = roles.get(0);
            String roleName = role.get("authority");
            Map<String, String> resultMap = new LinkedHashMap<>();
            resultMap.put(roleName, claims.getSubject());

            return resultMap;
        }
        return null;
    }

    public Date extractExpirationTimeFromToken(String theToken) {
        return extractClaim(theToken, Claims::getExpiration);
    }

    @Override
    public Boolean validateToken(String theToken, UserDto user) {
        final String userName = extractClaim(theToken, Claims::getSubject);
        return (userName.equals(user.getUsername()) && !isTokenExpired(theToken));
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignedKey()).build().parseClaimsJws(token).getBody();

    }

    private boolean isTokenExpired(String theToken) {
        return extractExpirationTimeFromToken(theToken).before(new Date());
    }

    private Key getSignedKey() {
        byte[] keyByte = Decoders.BASE64.decode(JWT_SECRET);
        return Keys.hmacShaKeyFor(keyByte);
    }
}
