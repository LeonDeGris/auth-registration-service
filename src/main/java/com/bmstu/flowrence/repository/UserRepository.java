package com.bmstu.flowrence.repository;

import com.bmstu.flowrence.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseEntityRepository<User> {

    Optional<User> findByEmail(String email);

}
