package com.bmstu.flowrence.mapper.dto;

import com.bmstu.flowrence.dto.response.UserInfoDto;
import com.bmstu.flowrence.entity.User;
import com.bmstu.flowrence.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper
public interface UserToUserInfoMapper extends BaseMapper<User, UserInfoDto> {

    @Override
    UserInfoDto mapSourceToDestination(User source);
}
