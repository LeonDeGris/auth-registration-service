package com.bmstu.flowrence.controller;

import com.bmstu.flowrence.auth.exception.UserAlreadyExistsException;
import com.bmstu.flowrence.auth.jwt.service.JwtTokenService;
import com.bmstu.flowrence.auth.service.UserAuthenticationService;
import com.bmstu.flowrence.dto.request.UserLoginCredentialsDto;
import com.bmstu.flowrence.dto.request.UserRegisterCredentialsDto;
import com.bmstu.flowrence.dto.response.JwtTokenDto;
import com.bmstu.flowrence.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserAuthenticationController {

    final private JwtTokenService jwtTokenService;
    final private UserAuthenticationService userAuthenticationService;

    // maybe some kind of annotation-based wrapper for error handling
    @PostMapping(value = "/create", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtTokenDto> createUser(@RequestBody UserRegisterCredentialsDto credentials) {
        log.debug("Creating user {}", credentials);
        try {
            User user = userAuthenticationService.create(credentials);

            return new ResponseEntity<>(JwtTokenDto.builder()
                    .jwtToken(jwtTokenService.createToken(user))
                    .userUuid(user.getUuid())
                    .build(), HttpStatus.OK);
        } catch (UserAlreadyExistsException e) {
            log.error("User already exists", e);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (Exception e) {
            log.error("Error creating user", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/login", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtTokenDto> login(@RequestBody UserLoginCredentialsDto credentials) {
        log.debug("Logging in with {}", credentials);
        try {
            Optional<User> optionalUser = userAuthenticationService.authorize(credentials);
            return optionalUser
                    .map(user -> new ResponseEntity<>(JwtTokenDto.builder()
                            .jwtToken(jwtTokenService.createToken(user))
                            .userUuid(user.getUuid())
                            .build(), HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
