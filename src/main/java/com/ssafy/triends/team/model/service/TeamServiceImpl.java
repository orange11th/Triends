package com.ssafy.triends.team.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.triends.team.model.Team;
import com.ssafy.triends.team.model.TeamMember;
import com.ssafy.triends.team.model.mapper.TeamMapper;

@Service
public class TeamServiceImpl implements TeamService {
	private final TeamMapper teamMapper;

	public TeamServiceImpl(TeamMapper teamMapper) {
		super();
		this.teamMapper = teamMapper;
	}

	@Override
	public List<Team> listTeamByUserId(String userId) {
		List<Team> teamIdList = teamMapper.selectTeamByUserId(userId);
		
		List<Team> teamList = new ArrayList<>();
		for(Team t : teamIdList) {
			Team tmpTeam = new Team();
			tmpTeam.setTeamId(t.getTeamId());
			tmpTeam.setTeamName(t.getTeamName());
			tmpTeam.setTeamList(teamMapper.selectTeamMember(tmpTeam.getTeamId()));
			teamList.add(tmpTeam);
		}
		return teamList;
	}

	@Override
	public void registTeam(Team team,String userId) {
		teamMapper.insertTeam(team);
		TeamMember teamMember = new TeamMember();
		teamMember.setTeamId(team.getTeamId());
		teamMember.setUserId(userId);
		teamMapper.insertTeamMember(teamMember);
	}

	@Override
	public void registMember(TeamMember teamMember) {
		teamMapper.insertTeamMember(teamMember);
	}
}
