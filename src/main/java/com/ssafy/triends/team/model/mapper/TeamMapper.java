package com.ssafy.triends.team.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.triends.member.model.Member;
import com.ssafy.triends.team.model.Team;
import com.ssafy.triends.team.model.TeamMember;

@Mapper
public interface TeamMapper {

	void insertTeam(Team team);

	void insertTeamMember(TeamMember tMem);

	List<Team> selectTeamByUserId(String userId);
	
	List<TeamMember> selectTeamMember(int teamId);

	List<Member> selectNotTeamMember(int teamId);
}
