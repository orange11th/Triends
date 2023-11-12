package com.ssafy.triends.attraction.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.triends.attraction.model.Attraction;

@Mapper
public interface AttractionMapper {

	List<Attraction> selectList(Attraction attraction);

	Attraction select(int contentId);

}
