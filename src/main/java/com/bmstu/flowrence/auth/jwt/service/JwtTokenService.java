package com.bmstu.flowrence.auth.jwt.service;

import com.bmstu.flowrence.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class JwtTokenService {

    JwtUserDetailsService userDetailsService;
    String secret;
    JwtParser jwtParser;
    @Value("${jwt.token.prefix:Bearer }")
    String jwtPrefix;

    public String createToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUuid());
        claims.put("email", user.getEmail());
        claims.put("password", user.getPassword());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        log.debug("Authenticating with token: {}", token);
        String uuid = extractIdentifier(token);
        log.debug("Extracted identifier {} from token {}", uuid, token);

        UserDetails userDetails = userDetailsService.loadUserByUsername(uuid);
        Claims claims = resolveClaims(token);
        
        if (userDetails.getPassword().equals(claims.get("password")))
            return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
        else
            throw new UsernameNotFoundException("User's passwords doesn't match");
    }

    public Optional<String> resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        log.debug("Got token: {}", bearerToken);
        return Optional.of(bearerToken)
                .filter(token -> token.startsWith(jwtPrefix))
                .map(token -> token.substring(jwtPrefix.length()));
    }

    private String extractIdentifier(String token) {
        return jwtParser
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private Claims resolveClaims(String token) {
        Claims claims = jwtParser
                .parseClaimsJws(token)
                .getBody();
        log.debug("Resolved claims: {}", claims);
        return claims;
    }
}
