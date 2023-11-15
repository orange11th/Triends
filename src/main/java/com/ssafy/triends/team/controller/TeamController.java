package com.ssafy.triends.team.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.triends.team.model.Team;
import com.ssafy.triends.team.model.service.TeamService;

@CrossOrigin("*")
@RestController
@RequestMapping("/team")
public class TeamController {
	private final TeamService teamService;

	public TeamController(TeamService teamService) {
		super();
		this.teamService = teamService;
	}
	
	@GetMapping("registTeam/{userId}/{teamName}")
	public ResponseEntity<String> regist(@PathVariable String teamName, @PathVariable String userId) {
		Team team = new Team();
		team.setTeamName(teamName);
		teamService.registTeam(team,userId);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping("registTeamMember/{userId}/{teamName}")
	public ResponseEntity<String> registMember(@PathVariable String teamName, @PathVariable String userId) {
//		Team team = new Team();
//		team.setTeamName(teamName);
//		teamService.registTeam(team,userId);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping("{userId}")
	public ResponseEntity<List<Team>> listTeamByUserId(@PathVariable String userId) {
		return ResponseEntity.ok().body(teamService.listTeamByUserId(userId));
	}
}
