package com.ssafy.triends.team.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.triends.team.model.Plan;
import com.ssafy.triends.team.model.PlanPlace;
import com.ssafy.triends.team.model.mapper.PlanMapper;

@Service
public class PlanServiceImpl implements PlanService {
	private final PlanMapper planMapper;
	
	public PlanServiceImpl(PlanMapper planMapper) {
		super();
		this.planMapper = planMapper;
	}

	@Override
	public void insertPlan(Plan plan) throws Exception {
		planMapper.insertPlan(plan);
	}

	@Override
	public List<Plan> listPlan(int teamId) throws Exception {
		return planMapper.listPlan(teamId);
	}

	@Override
	public void insertPlanPlace(PlanPlace planPlace) throws Exception {
		planMapper.insertPlanPlace(planPlace);
	}

	@Override
	public List<PlanPlace> listPlanPlace(int planId) throws Exception {
		return planMapper.listPlanPlace(planId);
	}

}
