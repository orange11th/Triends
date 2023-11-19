package com.ssafy.triends.team.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

//	@Override
//	public void insertPlan(Plan plan) throws Exception {
//		planMapper.insertPlan(plan);
//	}

	@Override
	public List<Plan> listPlan(int teamId) throws Exception {
		return planMapper.listPlan(teamId);
	}

//	@Override
//	public void insertPlanPlace(PlanPlace planPlace) throws Exception {
//		planMapper.insertPlanPlace(planPlace);
//	}

	@Override
	public List<PlanPlace> listPlanPlace(int planId) throws Exception {
		return planMapper.listPlanPlace(planId);
	}
	
	
    @Transactional
    public void savePlans(int teamId, List<Map<String, Object>> plansData) throws Exception {
        planMapper.deleteByTeamId(teamId); // 기존 Plan 삭제

        for (Map<String, Object> planData : plansData) {
            Plan plan = new Plan();
            plan.setTeamId(teamId);
            plan.setDate(planData.get("date").toString());
            planMapper.insertPlan(plan); // 새 Plan 삽입

            int planId = plan.getPlanId(); // 삽입된 Plan의 ID 가져오기

            List<Integer> placeIds = (List<Integer>) planData.get("places");
            for (int i = 0; i < placeIds.size(); i++) {
                PlanPlace planPlace = new PlanPlace();
                planPlace.setPlanId(planId);
                planPlace.setPlaceId(placeIds.get(i));
                planPlace.setOrder(i + 1);
                planMapper.insertPlanPlace(planPlace); // 새 PlanPlace 삽입
            }
        }
    }

}
