package com.bmstu.flowrence.mapper.dto;

import com.bmstu.flowrence.dto.response.DashboardInfoDto;
import com.bmstu.flowrence.entity.Dashboard;
import com.bmstu.flowrence.mapper.BaseMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(uses = {UserToUserInfoMapper.class, TaskToTaskInfoMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DashboardToDashboardInfoMapper extends BaseMapper<Dashboard, DashboardInfoDto> {

    @Override
    DashboardInfoDto mapSourceToDestination(Dashboard source);
}
