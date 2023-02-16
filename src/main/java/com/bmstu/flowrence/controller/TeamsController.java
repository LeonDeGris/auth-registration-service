package com.bmstu.flowrence.controller;

import com.bmstu.flowrence.dto.request.TeamCreateRequestDto;
import com.bmstu.flowrence.dto.request.personal.ListTeamsRequestDto;
import com.bmstu.flowrence.dto.request.personal.UserToTeamActionRequestDto;
import com.bmstu.flowrence.dto.response.TeamInfoDto;
import com.bmstu.flowrence.entity.Team;
import com.bmstu.flowrence.mapper.dto.TeamToTeamInfoMapper;
import com.bmstu.flowrence.service.TeamsService;
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
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamsController {

    private final TeamsService teamsService;
    private final TeamToTeamInfoMapper teamToTeamInfoMapper;

    // maybe some kind of annotation-based wrapper for error handling
    @PostMapping(value = "/create", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TeamInfoDto> createTeam(@RequestBody TeamCreateRequestDto request) {
        log.debug("Creating team with name {}", request.getName());
        try {
            Team teams = teamsService.createTeam(request.getName());
            TeamInfoDto responseDto = teamToTeamInfoMapper.mapSourceToDestination(teams);

            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error processing request", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/list", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TeamInfoDto>> listAllTeams(@RequestBody ListTeamsRequestDto request) {
        String userUuid = request.getUserUuid();
        log.debug("Fetching teams for user {}", userUuid);
        try {
            List<Team> teams = teamsService.listAllUsersTeams(userUuid);
            List<TeamInfoDto> responseDtoList = teamToTeamInfoMapper.mapSourceToDestination(teams);

            return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error processing request", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/enlist", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TeamInfoDto> addUserToTeam(@RequestBody UserToTeamActionRequestDto request) {
        log.debug("Adding user {} to team {}", request.getUserUuid(), request.getTeamUuid());
        try {
            Team team = teamsService.addUserToTeam(request.getUserUuid(), request.getTeamUuid());
            TeamInfoDto responseDto = teamToTeamInfoMapper.mapSourceToDestination(team);

            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error processing request", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/retire", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TeamInfoDto> removeUserFromTeam(@RequestBody UserToTeamActionRequestDto request) {
        log.debug("Removing user {} to team {}", request.getUserUuid(), request.getTeamUuid());
        try {
            Team team = teamsService.removeUserFromTeam(request.getUserUuid(), request.getTeamUuid());
            TeamInfoDto responseDto = teamToTeamInfoMapper.mapSourceToDestination(team);

            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error processing request", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
