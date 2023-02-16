package com.bmstu.flowrence.service;

import com.bmstu.flowrence.entity.User;
import com.bmstu.flowrence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService extends AbstractEntityService<User, UserRepository> {

}
