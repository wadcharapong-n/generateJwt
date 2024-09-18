package com.north.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jwt")
@RequiredArgsConstructor
public class JwtController {

    private final JwtService jwtService;

    @GetMapping
    public String getJwt(@RequestParam(name = "secretKey", required = false) String secretKey) {
        return jwtService.generateToken(secretKey);
    }

}
