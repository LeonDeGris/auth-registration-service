package com.bmstu.flowrence.auth.service;

import com.bmstu.flowrence.auth.exception.UserAlreadyExistsException;
import com.bmstu.flowrence.dto.request.UserLoginCredentialsDto;
import com.bmstu.flowrence.dto.request.UserRegisterCredentialsDto;
import com.bmstu.flowrence.entity.User;
import com.bmstu.flowrence.mapper.dto.RegisterCredentialsToUserMapper;
import com.bmstu.flowrence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserAuthenticationService {

    final private UserRepository userRepository;
    final private RegisterCredentialsToUserMapper registerCredentialsToUserMapper;

    public User create(UserRegisterCredentialsDto credentials) {
        String email = credentials.getEmail();
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent())
            throw new UserAlreadyExistsException(email);

        User user = registerCredentialsToUserMapper.mapSourceToDestination(credentials);

        // TODO: add BCrypt e.g. via wrapper (decorator on repository)
        return userRepository.save(user);
    }

    public Optional<User> authorize(UserLoginCredentialsDto credentials) {
        Optional<User> userOptional = userRepository.findByEmail(credentials.getEmail());

        // TODO: add BCrypt e.g. via wrapper (decorator on repository)
        return userOptional
                .filter(user -> user.getPassword().equals(credentials.getPassword()
                ));
    }
}
