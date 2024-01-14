package com.nurik.userservice.security.jwt;

import com.nurik.userservice.security.service.userdetails.CustomUserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtUtils {
    @Value("${service.app.secret.jwtCookieName}")
    private String jwtCookieName;

    @Value("${service.app.secret.jwtSecret}")
    private String jwtSecret;

    @Value("${service.app.secret.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {
        CustomUserDetailsImpl userPrincipal = (CustomUserDetailsImpl) authentication.getPrincipal();
        Date issuedAt = new Date();

        return "Bearer " + Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(issuedAt)
                .setExpiration(new Date(issuedAt.getTime() + jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    public ResponseCookie generateJwtCookie(Authentication authentication) {
        String jwtToken = generateJwtToken(authentication);

        return ResponseCookie
                .from(jwtCookieName, jwtToken)
                .path("/users")
                .maxAge(60*60)
                .httpOnly(true)
                .build();
    }

    public ResponseCookie getCleanJwtCookie() {
        return ResponseCookie
                .from(jwtCookieName, null)
                .path("/users").
                build();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key())
                    .build()
                .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            String removeBearerPrefixToken = removeBearerPrefix(authToken);
            Jwts.parserBuilder()
                    .setSigningKey(key())
                        .build()
                    .parse(removeBearerPrefixToken);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    public String removeBearerPrefix(String authToken) {
        if (StringUtils.hasText(authToken) && authToken.startsWith("Bearer ")) {
            return authToken.substring(7);
        }
        return null;
    }

}
