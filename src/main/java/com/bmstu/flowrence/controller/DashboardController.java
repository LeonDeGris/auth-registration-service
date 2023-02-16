package com.bmstu.flowrence.controller;

import com.bmstu.flowrence.dto.request.DashboardCreateRequestDto;
import com.bmstu.flowrence.dto.response.DashboardInfoDto;
import com.bmstu.flowrence.dto.response.TeamInfoDto;
import com.bmstu.flowrence.entity.Dashboard;
import com.bmstu.flowrence.mapper.dto.DashboardToDashboardInfoMapper;
import com.bmstu.flowrence.service.DashboardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;
    private final DashboardToDashboardInfoMapper dashboardToDashboardInfoMapper;

    // maybe some kind of annotation-based wrapper for error handling
    @PostMapping(value = "/create", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<DashboardInfoDto> createDashboard(@RequestBody DashboardCreateRequestDto request) {
        log.debug("Creating dashboard with name {}", request.getName());
        try {
            Dashboard teams = dashboardService.createDashboard(request);
            DashboardInfoDto responseDto = dashboardToDashboardInfoMapper.mapSourceToDestination(teams);

            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error processing request", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/list", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DashboardInfoDto>> listDashboards() {
        log.debug("Listing all dashboards");
        try {
            List<Dashboard> dashboards = dashboardService.listAllDashboards();
            List<DashboardInfoDto> responseDto = dashboardToDashboardInfoMapper.mapSourceToDestination(dashboards);

            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error processing request", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
