package com.north.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private String secretKey = "8f280f90b78ba6f501b11fce963460b1714437a239c8641edbb3ab0618c2ce8d"; // jwtSecretKey from your JS example

    public String generateToken(String key) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("bottler", "thainamtip");
        claims.put("clientId", "502029872");
        claims.put("channel", "line");
        claims.put("alternativeAccount", "66815859600");
        claims.put("userSessionId", "c4e13386-2838-4369-905e-edfb12575497");
        claims.put("userId", 9);
        claims.put("iat", 1726460289); // Unix timestamp in seconds
        claims.put("exp", 1726546689); // Unix timestamp in seconds
        return createToken(claims, key);
    }

    private String createToken(Map<String, Object> claims, String key) {
        long expirationTimeLong = 1000 * 60 * 60 * 24; // 24 hours in milliseconds
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expirationTimeLong);
        Key signKey = StringUtils.hasText(key) ? getKey(key) : getKey();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(signKey)
                .compact();
    }

    private Key getKey() {
        // Converts the secret string into a key object for jjwt library
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    private Key getKey(String key) {
        // Converts the secret string into a key object for jjwt library
        return Keys.hmacShaKeyFor(key.getBytes());
    }

    public Claims verifyToken(String token) {
        // Parse the token and return the body claims
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public void processToken(String token) {
        try {
            Claims claims = verifyToken(token);
            System.out.println("Token valid. Claims retrieved: " + claims);
        } catch (Exception e) {
            System.out.println("Token validation failed: " + e.getMessage());
        }
    }
}

