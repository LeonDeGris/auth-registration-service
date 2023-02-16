package com.bmstu.flowrence.mapper;

import com.bmstu.flowrence.auth.jwt.user.JwtUser;
import com.bmstu.flowrence.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserToJwtUserMapper extends BaseMapper<User, JwtUser> {

    @Mapping(target = "userIdentifier", source = "uuid")
    @Mapping(target = "userEmail", source = "email")
    @Mapping(target = "userPassword", source = "password")
    JwtUser mapSourceToDestination(User source);
}
