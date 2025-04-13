package com.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtility {

    @Value("${jwt.secret}")
    private String secret; // Secret key comes from application.properties

    private Key jwtSecretKey;
    private final long jwtExpirationMs = 86400000; // 1 day

    @PostConstruct
    public void init() {
        byte[] decodedKey = Base64.getDecoder().decode(secret);
        jwtSecretKey = Keys.hmacShaKeyFor(decodedKey); // Builds key from shared secret
    }

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        
        // Extract roles from authentication
        List<String> roles = authentication.getAuthorities().stream()
                .map(role -> role.getAuthority())
                .collect(Collectors.toList());

        // Include roles in the JWT claims
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles) // Add roles claim
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(jwtSecretKey)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtSecretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecretKey).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("Invalid JWT: " + e.getMessage());
        }
        return false;
    }

    public List<String> getRolesFromToken(String token) {
        Claims claims = extractClaims(token);
        return claims.get("roles", List.class); // Assuming the roles are stored under the 'roles' claim
    }

    // Method to extract claims from the token
    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtSecretKey) // Use the correct key
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
