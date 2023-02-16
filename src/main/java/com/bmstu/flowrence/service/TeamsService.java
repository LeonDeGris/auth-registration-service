package com.bmstu.flowrence.service;

import com.bmstu.flowrence.entity.Team;
import com.bmstu.flowrence.entity.User;
import com.bmstu.flowrence.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamsService extends AbstractEntityService<Team, TeamRepository> {

    private final UserService userService;

    public Team createTeam(String name) {
        Team team = new Team()
                .setName(name);
        return repository.save(team);
    }

    public List<Team> listAllUsersTeams(String userIdentifier) {
        User user = userService.retrieveByIdentifier(userIdentifier);
        return repository.findAllByUsersContaining(user);
    }

    public Team addUserToTeam(String userIdentifier, String teamIdentifier) {
        User user = userService.retrieveByIdentifier(userIdentifier);
        Team team = retrieveByIdentifier(teamIdentifier);

        team.getUsers().add(user);
        return repository.save(team);
    }

    public Team removeUserFromTeam(String userIdentifier, String teamIdentifier) {
        User user = userService.retrieveByIdentifier(userIdentifier);
        Team team = retrieveByIdentifier(teamIdentifier);

        team.getUsers().remove(user);
        return repository.save(team);
    }

}
