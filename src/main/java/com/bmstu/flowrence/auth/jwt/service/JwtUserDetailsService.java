package com.bmstu.flowrence.auth.jwt.service;

import com.bmstu.flowrence.auth.jwt.user.JwtUser;
import com.bmstu.flowrence.entity.User;
import com.bmstu.flowrence.mapper.UserToJwtUserMapper;
import com.bmstu.flowrence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserToJwtUserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        log.debug("Looking for the user by uuid {}", identifier);
        Optional<User> user = userRepository.findById(identifier);

        JwtUser jwtUser = user
                .map(userMapper::mapSourceToDestination)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with group: %s not found", identifier)));

        log.debug("User with group: {} successfully loaded", user);
        return jwtUser;
    }
}
