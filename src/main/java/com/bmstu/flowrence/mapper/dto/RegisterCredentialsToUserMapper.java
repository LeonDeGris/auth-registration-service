package com.bmstu.flowrence.mapper.dto;

import com.bmstu.flowrence.dto.request.UserRegisterCredentialsDto;
import com.bmstu.flowrence.entity.User;
import com.bmstu.flowrence.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper
public interface RegisterCredentialsToUserMapper extends BaseMapper<UserRegisterCredentialsDto, User> {

    @Override
    User mapSourceToDestination(UserRegisterCredentialsDto source);
}
