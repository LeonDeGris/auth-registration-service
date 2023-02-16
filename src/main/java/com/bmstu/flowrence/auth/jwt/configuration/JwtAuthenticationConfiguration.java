package com.bmstu.flowrence.auth.jwt.configuration;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Base64;

@Configuration
public class JwtAuthenticationConfiguration {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtParser jwtParser(String secret) {
        return Jwts.parser()
                .setSigningKey(secret);
    }

    @Bean
    public String secret(@Value("${jwt.token.secret}") String raw) {
        return Base64.getEncoder().encodeToString(raw.getBytes());
    }
}
