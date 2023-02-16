package com.bmstu.flowrence.repository;

import com.bmstu.flowrence.entity.Team;
import com.bmstu.flowrence.entity.User;

import java.util.List;

public interface TeamRepository extends BaseEntityRepository<Team> {

    List<Team> findAllByUsersContaining(User user);
}
