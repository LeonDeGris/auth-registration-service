package com.bmstu.flowrence.service;

import com.bmstu.flowrence.dto.request.DashboardCreateRequestDto;
import com.bmstu.flowrence.entity.Dashboard;
import com.bmstu.flowrence.entity.Team;
import com.bmstu.flowrence.repository.DashboardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class DashboardService extends AbstractEntityService<Dashboard, DashboardRepository> {

    private final TeamsService teamsService;

    public Dashboard createDashboard(DashboardCreateRequestDto request) {
        Team team = teamsService.retrieveByIdentifier(request.getTeamUuid());
        Dashboard dashboard = new Dashboard()
                .setName(request.getName())
                .setDescription(request.getDescription())
                .setPrefix(request.getPrefix())
                .setOwner(team);
        return repository.save(dashboard);
    }

    public List<Dashboard> listAllDashboards() {
        return repository.findAll();
    }

}
