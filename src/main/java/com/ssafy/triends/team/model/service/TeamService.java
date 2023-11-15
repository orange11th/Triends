package com.ssafy.triends.team.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.triends.team.model.Team;

@Service
public interface TeamService {

	List<Team> listTeamByUserId(String userId);

	void registTeam(Team t, String userId);

}
