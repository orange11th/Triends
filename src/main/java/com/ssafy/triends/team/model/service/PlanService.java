package com.ssafy.triends.team.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.triends.board.model.BoardDto;
import com.ssafy.triends.board.model.BoardListDto;
import com.ssafy.triends.board.model.LikeDto;
import com.ssafy.triends.team.model.Plan;
import com.ssafy.triends.team.model.PlanPlace;
import com.ssafy.triends.team.model.Team;
import com.ssafy.triends.team.model.TeamMember;

@Service
public interface PlanService {
	
	void insertPlan(Plan plan) throws Exception;
	List<Plan> listPlan(int teamId) throws Exception;
	void insertPlanPlace(PlanPlace planPlace) throws Exception;
	List<PlanPlace> listPlanPlace(int planId) throws Exception;
	
}
