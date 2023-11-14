package com.ssafy.triends.team.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.triends.team.model.Team;
import com.ssafy.triends.team.model.mapper.TeamMapper;

@Service
public class TeamServiceImpl implements TeamService {
	private final TeamMapper teamMapper;

	public TeamServiceImpl(TeamMapper teamMapper) {
		super();
		this.teamMapper = teamMapper;
	}

	@Override
	public List<Team> listTeamByUserId(int userId) {
		List<Team> list = new ArrayList<>();
		
		return list;
	}

	@Override
	public void registTeam(String teamName) {
		teamMapper.registTeam(teamName);
	}
}
