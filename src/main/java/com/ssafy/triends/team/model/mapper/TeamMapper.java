package com.ssafy.triends.team.model.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeamMapper {

	void registTeam(String teamName);
	
}
