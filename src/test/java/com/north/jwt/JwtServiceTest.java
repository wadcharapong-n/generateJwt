package com.north.jwt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    @Test
    void generateToken() {
        JwtService jwtService = new JwtService();
        String key = "8f280f90b78ba6f501b11fce963460b1714437a239c8641edbb3ab0618c2ce8d";
        String token = jwtService.generateToken(key);
        System.out.println(token);
        assertNotNull(token);
    }
}