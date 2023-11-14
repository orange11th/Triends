package com.ssafy.triends.team.model;

import java.util.List;

import lombok.Data;

@Data
public class Team {
	private int teamId;
	private String teamName;
	private List<TeamMember> teamList;
}
