package com.bmstu.flowrence.mapper.dto;

import com.bmstu.flowrence.dto.response.TeamInfoDto;
import com.bmstu.flowrence.entity.Team;
import com.bmstu.flowrence.mapper.BaseMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = UserToUserInfoMapper.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TeamToTeamInfoMapper extends BaseMapper<Team, TeamInfoDto> {

    @Override
    @Mapping(target = "teamMembers", source = "users")
    TeamInfoDto mapSourceToDestination(Team source);
}
