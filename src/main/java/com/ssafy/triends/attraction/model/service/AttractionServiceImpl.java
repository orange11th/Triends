package com.ssafy.triends.attraction.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.triends.attraction.model.Attraction;
import com.ssafy.triends.attraction.model.mapper.AttractionMapper;

@Service
public class AttractionServiceImpl implements AttractionService {
	private final AttractionMapper attractionMapper;
	
	public AttractionServiceImpl(AttractionMapper attractionMapper) {
		super();
		this.attractionMapper = attractionMapper;
	}

	@Override
	public List<Attraction> listAttraction(Attraction attraction) {
		return attractionMapper.selectList(attraction);
	}

	@Override
	public Attraction detailAttraciton(int contentId) {
		return attractionMapper.select(contentId);
	}
}
